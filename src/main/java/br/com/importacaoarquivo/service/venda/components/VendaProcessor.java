package br.com.importacaoarquivo.service.venda.components;

import br.com.importacaoarquivo.model.ItemVenda;
import br.com.importacaoarquivo.model.Venda;
import br.com.importacaoarquivo.model.Vendedor;
import br.com.importacaoarquivo.service.interfaces.ProcessaLinha;
import br.com.importacaoarquivo.service.vendedor.VendedorService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class VendaProcessor implements ProcessaLinha {

    private static final int POSICAO_VENDA = 1;
    private static final int POSICAO_VENDEDOR = 3;
    private static final int POSICAO_QUANTIDADE = 1;
    private static final int POSICAO_ID_ITEM = 0;
    private static final int POSICAO_ITEM = 2;
    private static final int POSICAO_PRECO = 2;
    private static final String SEPARADOR_ITEM = ",";
    private static final String SEPARADOR_CAMPOS_ITEM = "-";

    private final VendedorService vendedorService;

    public VendaProcessor(VendedorService vendedorService) {
        this.vendedorService = vendedorService;
    }

    @Override
    public Venda processarLinha(String[] linha) {
        int id = Integer.parseInt(linha[POSICAO_VENDA]);
        String item = linha[POSICAO_ITEM];
        item = item.substring(1, item.length() - 2);
        String nomeVendedor = linha[POSICAO_VENDEDOR];
        Vendedor vendedor = vendedorService.getNomePiorVendedor(nomeVendedor);
        Venda venda = new Venda(id, vendedor, nomeVendedor);
        String[] itens = item.split(SEPARADOR_ITEM);

        return processarItens(venda, itens);
    }

    private Venda processarItens(Venda venda, String[] itens) {
        for (String itemAtual : itens) {
            String[] campos = itemAtual.split(SEPARADOR_CAMPOS_ITEM);
            Long id = Long.parseLong(campos[POSICAO_ID_ITEM]);
            Double quantidade = Double.parseDouble(campos[POSICAO_QUANTIDADE]);
            BigDecimal preco = BigDecimal.valueOf(Double.parseDouble(campos[POSICAO_PRECO]));
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