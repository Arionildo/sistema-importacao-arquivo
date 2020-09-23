package br.com.importacaoarquivo.service.venda;

import br.com.importacaoarquivo.model.Venda;
import br.com.importacaoarquivo.service.venda.components.VendaData;
import br.com.importacaoarquivo.service.venda.components.VendaProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VendaService {

    private final Logger logger = LoggerFactory.getLogger(VendaService.class);
    private final VendaData vendaData;
    private final VendaProcessor vendaProcessor;

    @Autowired
    public VendaService(VendaData vendaData, VendaProcessor vendaProcessor) {
        this.vendaData = vendaData;
        this.vendaProcessor = vendaProcessor;
    }

    public Venda processarLinha(String[] linha) {
        Venda venda = vendaProcessor.processarLinha(linha);
        return adicionarVenda(venda);
    }

    public Venda adicionarVenda(Venda venda) {
        return vendaData.adicionarVenda(venda);
    }

    public Long getIdVendaMaisCara() {
        logger.info("Obtendo o Id da venda mais cara");
        return vendaData.getIdMaiorVenda();
    }

    public String getNomePiorVendedor() {
        logger.info("Obtendo o nome do vendedor");
        return vendaData.getNomePiorVendedor();
    }

    public void limparLista() {
        vendaData.limparLista();
        logger.info("Lista de Vendas Reiniciada");
    }
}
