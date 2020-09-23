package br.com.importacaoarquivo.service.vendedor.components;

import br.com.importacaoarquivo.model.Vendedor;
import br.com.importacaoarquivo.service.interfaces.ProcessaLinha;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class VendedorProcessor implements ProcessaLinha {

    private static final int POSICAO_CPF = 1;
    private static final int POSICAO_NOME = 2;
    private static final int POSICAO_SALARIO = 3;

    @Override
    public Vendedor processarLinha(String[] linha) {
        String cpf = linha[POSICAO_CPF];
        String nome = linha[POSICAO_NOME];
        BigDecimal salario = BigDecimal.valueOf(Double.parseDouble(linha[POSICAO_SALARIO]));
        return Vendedor.builder()
                .cpf(cpf)
                .nome(nome)
                .salario(salario)
                .build();
    }
}
