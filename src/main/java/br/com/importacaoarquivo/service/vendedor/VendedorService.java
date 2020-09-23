package br.com.importacaoarquivo.service.vendedor;

import br.com.importacaoarquivo.model.Vendedor;
import br.com.importacaoarquivo.service.vendedor.components.VendedorData;
import br.com.importacaoarquivo.service.vendedor.components.VendedorProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class VendedorService {

    private final VendedorData vendedorData;
    private final VendedorProcessor vendedorProcessor;
    private final Logger logger = LoggerFactory.getLogger(VendedorService.class);

    public VendedorService(VendedorData vendedorData, VendedorProcessor vendedorProcessor) {
        this.vendedorData = vendedorData;
        this.vendedorProcessor = vendedorProcessor;
    }

    public Vendedor processarLinha(String[] line) {
        Vendedor vendedor = vendedorProcessor.processarLinha(line);
        return adicionarVendedor(vendedor);
    }

    private Vendedor adicionarVendedor(Vendedor vendedor) {
        return vendedorData.adicionarVendedor(vendedor);
    }

    public Integer getQuantidadeVendedores() {
        logger.info("Obtendo a quantidade total de vendedores");
        return vendedorData.getQuantidadeVendedores();
    }

    public Vendedor getNomePiorVendedor(String name) {
        return vendedorData.getVendedorByNome(name);
    }

    public void limparLista() {
        vendedorData.limparLista();
        logger.info("Lista de Vendedores Reiniciada");
    }
}
