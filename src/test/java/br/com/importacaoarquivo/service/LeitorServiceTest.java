package br.com.importacaoarquivo.service;

import br.com.importacaoarquivo.mocker.ClienteMocker;
import br.com.importacaoarquivo.mocker.VendaMocker;
import br.com.importacaoarquivo.dto.RelatorioDTO;
import br.com.importacaoarquivo.service.cliente.components.ClienteData;
import br.com.importacaoarquivo.service.cliente.components.ClienteProcessor;
import br.com.importacaoarquivo.service.cliente.ClienteService;
import br.com.importacaoarquivo.service.dados.LeitorService;
import br.com.importacaoarquivo.service.dados.RelatorioService;
import br.com.importacaoarquivo.service.venda.components.VendaData;
import br.com.importacaoarquivo.service.venda.components.VendaProcessor;
import br.com.importacaoarquivo.service.venda.VendaService;
import br.com.importacaoarquivo.service.vendedor.components.VendedorData;
import br.com.importacaoarquivo.service.vendedor.components.VendedorProcessor;
import br.com.importacaoarquivo.service.vendedor.VendedorService;
import br.com.importacaoarquivo.mocker.VendedorMocker;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LeitorServiceTest {

    private static final String SEPARATOR = "ç";

    private VendedorService vendedorService;
    private ClienteService clienteService;
    private VendaService vendaService;
    private LeitorService leitorService;
    private RelatorioService relatorioService;

    @Before
    public void setUp() {
        VendedorData vendedorData = new VendedorData();
        VendedorProcessor vendedorProcessor = new VendedorProcessor();
        vendedorService = new VendedorService(vendedorData, vendedorProcessor);

        ClienteData clienteData = new ClienteData();
        ClienteProcessor clienteProcessor = new ClienteProcessor();
        clienteService = new ClienteService(clienteData, clienteProcessor);

        VendaData vendaData = new VendaData();
        VendaProcessor vendaProcessor = new VendaProcessor(vendedorService);
        vendaService = new VendaService(vendaData, vendaProcessor);

        leitorService = new LeitorService(clienteService, vendedorService, vendaService, SEPARATOR);
        relatorioService = new RelatorioService();
    }

    @Test
    public void deveProcessarCadaLinhaQuandoObtidaLinhaDeCadaTipo() {
        String[] listaVendedor = VendedorMocker.criarDuasLinhas();
        String[] listaCliente = ClienteMocker.criarDuasLinhas();
        String[] listaVenda = VendaMocker.criarDuasLinhas();
        String nomePiorVendedorEsperado = "Paulo";
        Integer quantidadeClientesEsperado = 2;
        Long idVendaMaisCaraEsperado = 8L;
        Integer quantidadeVendedoresEsperado = 2;

        String source = listaVendedor[0] + "\r\n" + listaVendedor[1]
                + "\r\n" + listaCliente[0] + "\r\n" + listaCliente[1]
                + "\r\n" + listaVenda[0] + "\r\n" + listaVenda[1];
        RelatorioDTO relatorioDTO = leitorService.processar(source);

        String relatorioObtido = relatorioService.salvar(relatorioDTO);

        assertEquals(nomePiorVendedorEsperado, relatorioDTO.getNomePiorVendedor());
        assertEquals(quantidadeClientesEsperado, relatorioDTO.getQuantidadeClientes());
        assertEquals(idVendaMaisCaraEsperado, relatorioDTO.getIdVendaMaisCara());
        assertEquals(quantidadeVendedoresEsperado, relatorioDTO.getQuantidadeVendedores());
        assertEquals(relatorioDTO.gerarRelatorio(), relatorioObtido);
    }

    @Test
    public void deveLimparListaEntaoMostrarQuandoNaoHouverNomePiorVendedor() {
        String[] listaVendedor = VendedorMocker.criarDuasLinhas();
        String[] listaCliente = ClienteMocker.criarDuasLinhas();
        String[] listaVenda = VendaMocker.criarDuasLinhas();
        Long idVendaMaisCaraEsperado = 0L;
        Integer quantidadeClientesEsperado = 0;
        Integer quantidadeVendedoresEsperado = 0;

        String source = listaVendedor[0] +
                "\r\n" +
                listaVendedor[1] +
                "\r\n" +
                listaCliente[0] +
                "\r\n" +
                listaCliente[1] +
                "\r\n" +
                listaVenda[0] +
                "\r\n" +
                listaVenda[1];
        leitorService.processar(source);

        vendedorService.limparLista();
        vendaService.limparLista();
        clienteService.limparLista();

        assertEquals("Não encontrado", vendaService.getNomePiorVendedor());
        assertEquals(idVendaMaisCaraEsperado, vendaService.getIdVendaMaisCara());
        assertEquals(quantidadeClientesEsperado, clienteService.getTotalClientes());
        assertEquals(quantidadeVendedoresEsperado, vendedorService.getQuantidadeVendedores());
    }
}