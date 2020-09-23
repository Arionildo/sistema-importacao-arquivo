package br.com.importacaoarquivo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class ItemVenda implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Double quantidade;
    private BigDecimal preco;

    public BigDecimal getValorTotal() {
        return preco.multiply(BigDecimal.valueOf(quantidade));
    }
}
