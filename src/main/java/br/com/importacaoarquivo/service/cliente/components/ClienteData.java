package br.com.importacaoarquivo.service.cliente.components;

import br.com.importacaoarquivo.model.Cliente;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class ClienteData {

    private final HashMap<String, Cliente> clienteHashMap;

    public ClienteData() {
        clienteHashMap = new HashMap<>();
    }

    public Cliente adicionarCliente(Cliente cliente) {
        clienteHashMap.put(cliente.getCnpj(), cliente);
        return clienteHashMap.get(cliente.getCnpj());
    }

    public int getTotalClientes() {
        return clienteHashMap.size();
    }

    public void limparLista() {
        clienteHashMap.clear();
    }
}
