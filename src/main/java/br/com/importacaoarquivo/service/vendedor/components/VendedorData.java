package br.com.importacaoarquivo.service.vendedor.components;

import br.com.importacaoarquivo.model.Vendedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class VendedorData {

    private final HashMap<String, Vendedor> vendedorHashMap;

    @Autowired
    public VendedorData() {
        vendedorHashMap = new HashMap<>();
    }

    public Vendedor adicionarVendedor(Vendedor vendedor) {
        return vendedorHashMap.put(vendedor.getCpf(), vendedor);
    }

    public Vendedor getVendedorByNome(String nome) {
        return vendedorHashMap.values()
                .stream()
                .filter(vendedor -> vendedor.getNome().equals(nome))
                .findFirst()
                .orElseGet(Vendedor::new);
    }

    public int getQuantidadeVendedores() {
        return vendedorHashMap.size();
    }

    public void limparLista() {
        vendedorHashMap.clear();
    }
}
