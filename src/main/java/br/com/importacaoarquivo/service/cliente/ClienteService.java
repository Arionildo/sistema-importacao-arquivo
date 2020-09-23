package br.com.importacaoarquivo.service.cliente;

import br.com.importacaoarquivo.model.Cliente;
import br.com.importacaoarquivo.service.cliente.components.ClienteData;
import br.com.importacaoarquivo.service.cliente.components.ClienteProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    private final Logger logger = LoggerFactory.getLogger(ClienteService.class);
    private final ClienteData clienteData;
    private final ClienteProcessor clienteProcessor;

    public ClienteService(ClienteData clienteData, ClienteProcessor clienteProcessor) {
        this.clienteData = clienteData;
        this.clienteProcessor = clienteProcessor;
    }

    public Cliente processarLinha(String[] linha) {
        Cliente cliente = clienteProcessor.processarLinha(linha);
        return adicionarCliente(cliente);
    }

    private Cliente adicionarCliente(Cliente cliente) {
        return clienteData.adicionarCliente(cliente);
    }

    public Integer getTotalClientes() {
        return clienteData.getTotalClientes();
    }

    public void limparLista() {
        clienteData.limparLista();
        logger.info("Lista de Clientes Reiniciada");
    }
}
