package br.com.importacaoarquivo.service.cliente;

import br.com.importacaoarquivo.mocker.ClienteMocker;
import br.com.importacaoarquivo.model.Cliente;
import br.com.importacaoarquivo.service.cliente.components.ClienteProcessor;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ClienteProcessorTest {

    private ClienteProcessor clienteProcessor;

    @Before
    public void setUp() {
        clienteProcessor = mock(ClienteProcessor.class);
    }

    @Test
    public void deveProcessarLinhaQuandoReceberLinhaCliente() {
        String[] linhaCliente = ClienteMocker.criarUmaLinha();
        Cliente cliente = new Cliente("2345675434544345", "Jose da Silva", "Rural");

        when(clienteProcessor.processarLinha(linhaCliente)).thenReturn(cliente);

        Cliente obtido = clienteProcessor.processarLinha(linhaCliente);

        assertThat(obtido, instanceOf(Cliente.class));
        assertEquals(obtido, cliente);
    }
}
