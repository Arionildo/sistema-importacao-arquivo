package br.com.importacaoarquivo.service.vendedor;

import br.com.importacaoarquivo.mocker.VendedorMocker;
import br.com.importacaoarquivo.model.Vendedor;
import br.com.importacaoarquivo.service.vendedor.components.VendedorData;
import br.com.importacaoarquivo.service.vendedor.components.VendedorProcessor;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class VendedorServiceTest {
    private static final String SEPARATOR = "ç";
    private VendedorService vendedorService;

    @Before
    public void setUp() {
        VendedorProcessor vendedorProcessor = new VendedorProcessor();
        VendedorData vendedorData = new VendedorData();
        vendedorService = new VendedorService(vendedorData, vendedorProcessor);
    }

    @Test
    public void deveBuscarNomePiorVendedorQuandoObtidoStringNome() {
        String[] listaVendedor = VendedorMocker.criarUmaLinha();
        Vendedor vendedor = new Vendedor("1234567891234", "Pedro", BigDecimal.valueOf(40000.99));

        String[] linhaVendedor = listaVendedor[0].split(SEPARATOR);
        vendedorService.processarLinha(linhaVendedor);

        Vendedor obtido = vendedorService.getNomePiorVendedor(vendedor.getNome());
        int tamanhoObtido = vendedorService.getQuantidadeVendedores();

        assertEquals(vendedor.getNome(), obtido.getNome());
        assertEquals(vendedor.getCpf(), obtido.getCpf());
        assertEquals(1, tamanhoObtido);
    }
}