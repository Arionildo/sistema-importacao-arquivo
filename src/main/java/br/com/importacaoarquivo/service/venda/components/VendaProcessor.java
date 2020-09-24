package br.com.importacaoarquivo.service.venda.components;

import br.com.importacaoarquivo.enums.CampoItemVenda;
import br.com.importacaoarquivo.enums.CampoVenda;
import br.com.importacaoarquivo.model.ItemVenda;
import br.com.importacaoarquivo.model.Venda;
import br.com.importacaoarquivo.model.Vendedor;
import br.com.importacaoarquivo.service.interfaces.ProcessaLinha;
import br.com.importacaoarquivo.service.vendedor.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class VendaProcessor implements ProcessaLinha {

    private static final String SEPARADOR_ITEM = ",";
    private static final String SEPARADOR_CAMPOS_ITEM = "-";

    private final VendedorService vendedorService;

    @Autowired
    public VendaProcessor(VendedorService vendedorService) {
        this.vendedorService = vendedorService;
    }

    @Override
    public Venda processarLinha(String[] linha) {
        int id = Integer.parseInt(linha[CampoVenda.ID.getId()]);
        String item = linha[CampoVenda.ITEM.getId()];
        item = item.substring(1, item.length() - 2);
        String nomeVendedor = linha[CampoVenda.VENDEDOR.getId()];
        Vendedor vendedor = vendedorService.getNomePiorVendedor(nomeVendedor);
        Venda venda = new Venda(id, vendedor, nomeVendedor);
        String[] itens = item.split(SEPARADOR_ITEM);

        return processarItens(venda, itens);
    }

    private Venda processarItens(Venda venda, String[] itens) {
        for (String itemAtual : itens) {
            String[] campos = itemAtual.split(SEPARADOR_CAMPOS_ITEM);
            Long id = Long.parseLong(campos[CampoItemVenda.ID.getId()]);
            Double quantidade = Double.parseDouble(campos[CampoItemVenda.QUANTIDADE.getId()]);
            BigDecimal preco = BigDecimal.valueOf(Double.parseDouble(campos[CampoItemVenda.PRECO.getId()]));
            ItemVenda itemVenda = ItemVenda.builder()
                    .id(id)
                    .quantidade(quantidade)
                    .preco(preco)
                    .build();
            venda.adicionarItemVenda(itemVenda);
        }
        return venda;
    }
}