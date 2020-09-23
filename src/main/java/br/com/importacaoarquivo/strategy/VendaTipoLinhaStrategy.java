package br.com.importacaoarquivo.strategy;

import br.com.importacaoarquivo.service.venda.VendaService;
import br.com.importacaoarquivo.strategy.interfaces.TipoLinha;

public class VendaTipoLinhaStrategy implements TipoLinha {
	private static final int POSICAO_TIPO = 0;

	private final VendaService vendaService;

	public VendaTipoLinhaStrategy(VendaService vendaService) {
		this.vendaService = vendaService;
	}

	@Override
	public void processar(String linha, String separator) {
		String[] campos = linha.split(separator);
		String tipo = campos[POSICAO_TIPO];
		if ("003".equals(tipo)) {
			vendaService.processarLinha(campos);
		}
	}
}
