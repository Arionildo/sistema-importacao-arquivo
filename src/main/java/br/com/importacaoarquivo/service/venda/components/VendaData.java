package br.com.importacaoarquivo.service.venda.components;

import br.com.importacaoarquivo.model.Venda;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Component
public class VendaData {

    private final static long DEFAULT_ID_MAIOR_VENDA = 0;
    private final static String DEFAULT_NOME_PIOR_VENDEDOR = "Não encontrado";

    private final List<Venda> listaVenda;

    public VendaData() {
        this.listaVenda = new ArrayList<>();
    }

    public Venda adicionarVenda(Venda venda) {
        listaVenda.add(venda);
        return venda;
    }

    public Long getIdMaiorVenda() {
        return listaVenda.stream()
                .max(Comparator.comparing(Venda::getValor))
                .map(Venda::getId)
                .orElse(DEFAULT_ID_MAIOR_VENDA);
    }

    public String getNomePiorVendedor() {
        BigDecimal valorPrimeiraVenda = listaVenda.stream()
                .filter(Objects::nonNull)
                .filter(venda -> venda.getValor().compareTo(BigDecimal.ZERO) != 0)
                .findFirst()
                .map(Venda::getValor)
                .orElse(BigDecimal.ZERO);

        return listaVenda.stream()
                .filter(Objects::nonNull)
                .filter(venda -> venda.getValor().compareTo(BigDecimal.ZERO) != 0 && venda.getNomeVendedor() != null)
                .filter(venda -> venda.getValor().compareTo(valorPrimeiraVenda) < 0)
                .findFirst()
                .map(Venda::getNomeVendedor)
                .orElse(DEFAULT_NOME_PIOR_VENDEDOR);
    }

    public void limparLista() {
        listaVenda.clear();
    }
}