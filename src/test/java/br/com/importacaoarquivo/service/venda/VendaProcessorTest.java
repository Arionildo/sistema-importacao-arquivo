package br.com.importacaoarquivo.service.venda;

import br.com.importacaoarquivo.mocker.VendaMocker;
import br.com.importacaoarquivo.model.Venda;
import br.com.importacaoarquivo.model.Vendedor;
import br.com.importacaoarquivo.service.venda.components.VendaProcessor;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class VendaProcessorTest {

    private VendaProcessor vendaProcessor;

    @Before
    public void setUp() {
        vendaProcessor = mock(VendaProcessor.class);
    }

    @Test
    public void deveProcessarLinhaQuandoReceberLinhaVenda() {
        String[] linhaVenda = VendaMocker.criarUmaLinha();
        Vendedor vendedor = new Vendedor("3245678865434", "Paulo", BigDecimal.valueOf(40000.99));
        Venda venda = new Venda(8, vendedor, "Paulo");

        when(vendaProcessor.processarLinha(linhaVenda)).thenReturn(venda);

        Venda obtido = vendaProcessor.processarLinha(linhaVenda);

        assertThat(obtido, instanceOf(Venda.class));
        assertEquals(obtido, venda);
    }
}
