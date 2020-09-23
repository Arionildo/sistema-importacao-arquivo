package br.com.importacaoarquivo.service.dados;

import br.com.importacaoarquivo.dto.RelatorioDTO;
import br.com.importacaoarquivo.service.interfaces.SalvaRelatorio;
import org.springframework.stereotype.Service;

@Service
public class RelatorioService implements SalvaRelatorio {

    @Override
    public String salvar(RelatorioDTO relatorioDTO) {
        return relatorioDTO.gerarRelatorio();
    }
}
