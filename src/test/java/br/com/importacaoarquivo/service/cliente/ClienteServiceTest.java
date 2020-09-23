package br.com.importacaoarquivo.service.cliente;

import br.com.importacaoarquivo.mocker.ClienteMocker;
import br.com.importacaoarquivo.model.Cliente;
import br.com.importacaoarquivo.service.cliente.components.ClienteData;
import br.com.importacaoarquivo.service.cliente.components.ClienteProcessor;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ClienteServiceTest {

    private static final String SEPARATOR = "ç";
    private ClienteService clienteService;

    @Before
    public void setUp() {
        ClienteData clienteData = new ClienteData();
        ClienteProcessor clienteProcessor = new ClienteProcessor();
        clienteService = new ClienteService(clienteData, clienteProcessor);
    }

    @Test
    public void deveProcessarLinhaQuandoReceberLinhaClienteEntaoTrazerDadosETamanhoCorrespondente() {
        String[] listaCliente = ClienteMocker.criarUmaLinha();
        Cliente cliente = new Cliente("2345675434544345", "Jose da Silva", "Rural");

        String[] linhaCliente = listaCliente[0].split(SEPARATOR);
        Cliente clienteObtido = clienteService.processarLinha(linhaCliente);
        int tamanhoObtido = clienteService.getTotalClientes();

        assertEquals(cliente.getNome(), clienteObtido.getNome());
        assertEquals(cliente.getAreaNegocio(), clienteObtido.getAreaNegocio());
        assertEquals(cliente.getCnpj(), clienteObtido.getCnpj());
        assertEquals(1, tamanhoObtido);
    }
}