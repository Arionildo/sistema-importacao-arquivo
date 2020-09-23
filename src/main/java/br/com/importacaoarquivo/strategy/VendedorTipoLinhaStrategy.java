package br.com.importacaoarquivo.strategy;

import br.com.importacaoarquivo.service.vendedor.VendedorService;
import br.com.importacaoarquivo.strategy.interfaces.TipoLinha;

public class VendedorTipoLinhaStrategy implements TipoLinha {
	private static final int POSICAO_TIPO = 0;

	private final VendedorService vendedorService;

	public VendedorTipoLinhaStrategy(VendedorService vendedorService) {
		this.vendedorService = vendedorService;
	}

	@Override
	public void processar(String linha, String separator) {
		String[] campos = linha.split(separator);
		String tipo = campos[POSICAO_TIPO];
		if ("001".equals(tipo)) {
			vendedorService.processarLinha(campos);
		}
	}
}
