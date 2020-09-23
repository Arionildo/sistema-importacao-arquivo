package br.com.importacaoarquivo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class Venda implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private List<ItemVenda> listaItemVenda;
    private BigDecimal valor;
    private Vendedor vendedor;
    private String nomeVendedor;

    public Venda(long id, Vendedor vendedor, String nomeVendedor) {
        this.id = id;
        this.vendedor = vendedor;
        this.nomeVendedor = nomeVendedor;
        listaItemVenda = new ArrayList<>();
    }

    public void adicionarItemVenda(ItemVenda itemVenda) {
        if (isNull(valor)) {
            valor = BigDecimal.ZERO;
        }
        listaItemVenda.add(itemVenda);
        valor = valor.add(itemVenda.getValorTotal());
    }
}
