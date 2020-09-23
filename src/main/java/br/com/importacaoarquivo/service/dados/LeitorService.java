package br.com.importacaoarquivo.service.dados;

import br.com.importacaoarquivo.dto.RelatorioDTO;
import br.com.importacaoarquivo.service.cliente.ClienteService;
import br.com.importacaoarquivo.service.venda.VendaService;
import br.com.importacaoarquivo.service.vendedor.VendedorService;
import br.com.importacaoarquivo.strategy.ClienteTipoLinhaStrategy;
import br.com.importacaoarquivo.strategy.VendaTipoLinhaStrategy;
import br.com.importacaoarquivo.strategy.VendedorTipoLinhaStrategy;
import br.com.importacaoarquivo.strategy.interfaces.TipoLinha;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class LeitorService {
	private final Logger logger = LoggerFactory.getLogger(LeitorService.class);
	private final ClienteService clienteService;
	private final VendedorService vendedorService;
	private final VendaService vendaService;
	private final String separator;
	private final List<TipoLinha> listaTipoLinha;

	@Autowired
	public LeitorService(ClienteService clienteService,
	                     VendedorService vendedorService,
	                     VendaService vendaService,
	                     @Value("${separator}") String separator) {
		this.clienteService = clienteService;
		this.vendedorService = vendedorService;
		this.vendaService = vendaService;
		this.separator = separator;
		listaTipoLinha = Arrays.asList(new ClienteTipoLinhaStrategy(clienteService),
				new VendaTipoLinhaStrategy(vendaService),
				new VendedorTipoLinhaStrategy(vendedorService));
	}

	public RelatorioDTO processar(String conteudo) {
		logger.info("Processando o Conteúdo:\n{}", conteudo);
		List<String> linhas = Arrays.asList(conteudo.split(System.lineSeparator()));
		linhas.forEach(this::processarLinha);
		return RelatorioDTO
				.builder()
				.quantidadeClientes(clienteService.getTotalClientes())
				.idVendaMaisCara(vendaService.getIdVendaMaisCara())
				.quantidadeVendedores(vendedorService.getQuantidadeVendedores())
				.nomePiorVendedor(vendaService.getNomePiorVendedor())
				.build();
	}

	private void processarLinha(String linha) {
		logger.info("Processando a linha: {}", linha);
		listaTipoLinha.forEach(tipoLinha -> tipoLinha.processar(linha, separator));
	}
}
