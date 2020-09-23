package br.com.importacaoarquivo.service.vendedor;

import br.com.importacaoarquivo.model.Vendedor;
import br.com.importacaoarquivo.service.vendedor.components.VendedorData;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class VendedorDataTest {

    private VendedorData vendedorData;

    @Before
    public void setUp() {
        vendedorData = new VendedorData();
    }

    @Test
    public void deveAdicionarVendedorQuandoObtidoObjetoVendedorEntaoTrazerDadosEQuantidadeCorrespondente() {
        Vendedor vendedor = new Vendedor("1234567891234", "Pedro", BigDecimal.valueOf(40000.99));

        vendedorData.adicionarVendedor(vendedor);

        int obtido = vendedorData.getQuantidadeVendedores();
        Vendedor vendedorobtido = vendedorData.getVendedorByNome("Pedro");

        assertEquals(1, obtido);
        assertEquals("Pedro", vendedorobtido.getNome());
        assertEquals("1234567891234", vendedorobtido.getCpf());
    }

    @Test
    public void deveBuscarVendedorPorNomeQuandoObtidoStringNomeEntaoRetornarDadosCorrespondentes() {
        Vendedor vendedor = new Vendedor("1234567891234", "Pedro", BigDecimal.valueOf(40000.99));

        vendedorData.adicionarVendedor(vendedor);

        Vendedor vendedorobtido = vendedorData.getVendedorByNome("Pedro");

        assertEquals("Pedro", vendedorobtido.getNome());
        assertEquals("1234567891234", vendedorobtido.getCpf());
    }

    @Test
    public void deveBuscarCadaVendedorPorNomeQuandoObtidoStringNomeEntaoRetornarQuantidadeCorrespondente() {
        Vendedor vendedor = new Vendedor("1234567891234", "Pedro", BigDecimal.valueOf(40000.99));
        Vendedor vendedor2 = new Vendedor("1234567891222", "Paulo", BigDecimal.valueOf(5000));
        Vendedor vendedor3 = new Vendedor("1234567891221", "Joao", BigDecimal.valueOf(1000));

        vendedorData.adicionarVendedor(vendedor);
        vendedorData.adicionarVendedor(vendedor2);
        vendedorData.adicionarVendedor(vendedor3);

        int obtido = vendedorData.getQuantidadeVendedores();

        assertEquals(3, obtido);
    }

    @Test
    public void deveLimparLista() {
        Vendedor vendedor = new Vendedor("1234567891234", "Pedro", BigDecimal.valueOf(40000.99));
        vendedorData.adicionarVendedor(vendedor);
        vendedorData.limparLista();
        int obtido = vendedorData.getQuantidadeVendedores();
        assertEquals(0, obtido);
    }
}