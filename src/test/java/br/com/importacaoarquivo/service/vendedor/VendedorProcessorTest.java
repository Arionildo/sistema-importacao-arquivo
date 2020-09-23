package br.com.importacaoarquivo.service.vendedor;

import br.com.importacaoarquivo.mocker.VendedorMocker;
import br.com.importacaoarquivo.model.Vendedor;
import br.com.importacaoarquivo.service.vendedor.components.VendedorProcessor;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class VendedorProcessorTest {

    private VendedorProcessor vendedorProcessor;

    @Before
    public void setUp() {
        vendedorProcessor = mock(VendedorProcessor.class);
    }

    @Test
    public void deveProcessarLinhaQuandoObtidoLinhaVendedor() {
        String[] linhaVendedor = VendedorMocker.criarUmaLinha();
        Vendedor vendedor = new Vendedor("1234567891234", "Pedro", BigDecimal.valueOf(40000.99));

        when(vendedorProcessor.processarLinha(linhaVendedor)).thenReturn(vendedor);

        Vendedor obtido = vendedorProcessor.processarLinha(linhaVendedor);
        assertThat(obtido, instanceOf(Vendedor.class));
        assertEquals(obtido, vendedor);
    }
}