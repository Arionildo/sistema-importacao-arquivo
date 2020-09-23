package br.com.importacaoarquivo.configuration;

import br.com.importacaoarquivo.dto.RelatorioDTO;
import br.com.importacaoarquivo.service.cliente.ClienteService;
import br.com.importacaoarquivo.service.dados.LeitorService;
import br.com.importacaoarquivo.service.dados.RelatorioService;
import br.com.importacaoarquivo.service.venda.VendaService;
import br.com.importacaoarquivo.service.vendedor.VendedorService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.file.FileHeaders;
import org.springframework.integration.file.FileNameGenerator;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.dsl.FileInboundChannelAdapterSpec;
import org.springframework.integration.file.dsl.Files;
import org.springframework.integration.file.filters.SimplePatternFileListFilter;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.integration.handler.GenericHandler;
import org.springframework.integration.handler.LoggingHandler;
import org.springframework.integration.transformer.GenericTransformer;

import java.nio.file.Paths;

import static java.util.Objects.requireNonNull;

@Configuration
@EnableIntegration
public class IntegrationFlowConfiguration {

	private final Long period;
	private final int maxMessagesPerPoll;
	private final String fileType;
	private final String folderIn;
	private final String folderOut;
	private final ClienteService clienteService;
	private final VendedorService vendedorService;
	private final VendaService vendaService;
	private final LeitorService leitorService;
	private final RelatorioService relatorioService;

	public IntegrationFlowConfiguration(@Value("${poller.period}") Long period,
	                                    @Value("${poller.maxmessagesperpoll}") int maxMessagesPerPoll,
	                                    @Value("${file.in.type}") String fileType,
	                                    @Value("${file.in.folder}") String folderIn,
	                                    @Value("${file.out.folder}") String folderOut,
	                                    LeitorService leitorService,
	                                    RelatorioService relatorioService,
	                                    ClienteService clienteService,
	                                    VendedorService vendedorService,
	                                    VendaService vendaService) {
		this.period = period;
		this.maxMessagesPerPoll = maxMessagesPerPoll;
		this.fileType = fileType;
		this.folderIn = folderIn;
		this.folderOut = folderOut;
		this.leitorService = leitorService;
		this.relatorioService = relatorioService;
		this.clienteService = clienteService;
		this.vendedorService = vendedorService;
		this.vendaService = vendaService;
	}

	@Bean
	IntegrationFlow integrationFlow() {
		return IntegrationFlows
				.from(createFlowBuiler(),
						spec -> spec.poller(Pollers.fixedRate(period).maxMessagesPerPoll(maxMessagesPerPoll)))
				.filter(new SimplePatternFileListFilter(fileType))
				.log(LoggingHandler.Level.INFO, "Processamento de Arquivo Iniciado")
				.transform(Files.toStringTransformer())
				.transform(processData())
				.handle(generateMetricHandler())
				.log(LoggingHandler.Level.INFO, "Processamento de Arquivo Concluído")
				.handle(fileWritingMessageHandler())
				.get();
	}

	private FileInboundChannelAdapterSpec createFlowBuiler() {
		return Files.inboundAdapter(Paths.get(getBasePath(), folderIn).toFile())
				.useWatchService(Boolean.TRUE);
	}

	private GenericTransformer<String, RelatorioDTO> processData() {
		clearLists();
		return leitorService::processar;
	}

	public GenericHandler<RelatorioDTO> generateMetricHandler() {
		return (message, headers) -> relatorioService.salvar(message);
	}

	private FileWritingMessageHandler fileWritingMessageHandler() {
		return Files.outboundAdapter(Paths.get(getBasePath(), folderOut).toFile())
				.deleteSourceFiles(true)
				.fileExistsMode(FileExistsMode.REPLACE)
				.fileNameGenerator(getNameGenerator())
				.get();
	}

	private FileNameGenerator getNameGenerator() {
		return fileName -> requireNonNull(fileName.getHeaders()
				.get(FileHeaders.FILENAME, String.class))
				.replaceFirst("(.*)(\\.dat)", "$1.done$2");
	}

	private String getBasePath() {
		return System.getProperty("user.home");
	}

	private void clearLists() {
		vendedorService.limparLista();
		vendaService.limparLista();
		clienteService.limparLista();
	}
}
