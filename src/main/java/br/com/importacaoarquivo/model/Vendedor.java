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
public class Vendedor implements Serializable {

    private static final long serialVersionUID = 1L;

    private String cpf;
    private String nome;
    private BigDecimal salario;
}
