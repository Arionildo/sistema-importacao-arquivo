package br.com.importacaoarquivo.strategy;

import br.com.importacaoarquivo.service.cliente.ClienteService;
import br.com.importacaoarquivo.strategy.interfaces.TipoLinha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClienteTipoLinhaStrategy implements TipoLinha {
	private static final int POSICAO_TIPO = 0;

	private final ClienteService clienteService;

	@Autowired
	public ClienteTipoLinhaStrategy(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	@Override
	public void processar(String linha, String separator) {
		String[] campos = linha.split(separator);
		String tipo = campos[POSICAO_TIPO];
		if ("002".equals(tipo)) {
			clienteService.processarLinha(campos);
		}
	}
}
