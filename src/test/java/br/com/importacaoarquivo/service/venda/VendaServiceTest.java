package br.com.importacaoarquivo.service.venda;

import br.com.importacaoarquivo.mocker.VendaMocker;
import br.com.importacaoarquivo.mocker.VendedorMocker;
import br.com.importacaoarquivo.model.Venda;
import br.com.importacaoarquivo.model.Vendedor;
import br.com.importacaoarquivo.service.venda.components.VendaData;
import br.com.importacaoarquivo.service.venda.components.VendaProcessor;
import br.com.importacaoarquivo.service.vendedor.VendedorService;
import br.com.importacaoarquivo.service.vendedor.components.VendedorData;
import br.com.importacaoarquivo.service.vendedor.components.VendedorProcessor;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class VendaServiceTest {
    private static final String SEPARATOR = "ç";
    private VendaService vendaService;
    private VendedorService vendedorService;

    @Before
    public void setUp() {
        VendedorData vendedorData = new VendedorData();
        VendedorProcessor vendedorProcessor = new VendedorProcessor();
        vendedorService = new VendedorService(vendedorData, vendedorProcessor);
        VendaData vendaData = new VendaData();
        VendaProcessor vendaProcessor = new VendaProcessor(vendedorService);
        vendaService = new VendaService(vendaData, vendaProcessor);
    }

    @Test
    public void deveProcessarLinhaQuandoReceberLinhaVendaEntaoTrazerDadosETamanhoCorrespondente() {
        String[] listaVenda = VendaMocker.criarDuasLinhas();
        Vendedor vendedor = new Vendedor("1234567891234", "Pedro", BigDecimal.valueOf(40000.99));
        Venda venda = new Venda(8, vendedor, "Pedro");

        String[] listaVendedor = VendedorMocker.criarDuasLinhas();

        vendedorService.processarLinha(listaVendedor[0].split(SEPARATOR));
        vendedorService.processarLinha(listaVendedor[1].split(SEPARATOR));

        Venda obtido = vendaService.processarLinha(listaVenda[0].split(SEPARATOR));
        vendaService.processarLinha(listaVenda[1].split(SEPARATOR));

        long idVendaMaisCaraObtida = vendaService.getIdVendaMaisCara();
        String nomePiorVendedorObtido = vendaService.getNomePiorVendedor();

        assertThat(obtido, instanceOf(Venda.class));
        assertEquals(venda.getId(), obtido.getId());
        assertEquals(venda.getNomeVendedor(), obtido.getNomeVendedor());
        assertEquals(8, idVendaMaisCaraObtida);
        assertEquals("Paulo", nomePiorVendedorObtido);

        assertThat(obtido, instanceOf(Venda.class));
    }
}