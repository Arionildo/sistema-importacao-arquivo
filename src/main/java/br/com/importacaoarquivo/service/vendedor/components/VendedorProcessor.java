package br.com.importacaoarquivo.service.vendedor.components;

import br.com.importacaoarquivo.enums.CampoVendedor;
import br.com.importacaoarquivo.model.Vendedor;
import br.com.importacaoarquivo.service.interfaces.ProcessaLinha;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class VendedorProcessor implements ProcessaLinha {

    @Override
    public Vendedor processarLinha(String[] linha) {
        String cpf = linha[CampoVendedor.CPF.getId()];
        String nome = linha[CampoVendedor.NOME.getId()];
        BigDecimal salario = BigDecimal.valueOf(Double.parseDouble(linha[CampoVendedor.SALARIO.getId()]));
        return Vendedor.builder()
                .cpf(cpf)
                .nome(nome)
                .salario(salario)
                .build();
    }
}
