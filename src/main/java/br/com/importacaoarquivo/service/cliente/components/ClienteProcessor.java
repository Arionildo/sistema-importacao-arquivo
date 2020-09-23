package br.com.importacaoarquivo.service.cliente.components;

import br.com.importacaoarquivo.model.Cliente;
import br.com.importacaoarquivo.service.interfaces.ProcessaLinha;
import org.springframework.stereotype.Component;

@Component
public class ClienteProcessor implements ProcessaLinha {

    private static final int POSICAO_CNPJ = 1;
    private static final int POSICAO_NOME = 2;
    private static final int POSICAO_AREA_NEGOCIO = 3;

    @Override
    public Cliente processarLinha(String[] linha) {
        String cnpj = linha[POSICAO_CNPJ];
        String nome = linha[POSICAO_NOME];
        String areaNegocio = linha[POSICAO_AREA_NEGOCIO];
        return Cliente.builder()
                .cnpj(cnpj)
                .nome(nome)
                .areaNegocio(areaNegocio)
                .build();
    }
}
