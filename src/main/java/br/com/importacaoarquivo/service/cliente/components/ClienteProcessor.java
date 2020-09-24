package br.com.importacaoarquivo.service.cliente.components;

import br.com.importacaoarquivo.enums.CampoCliente;
import br.com.importacaoarquivo.model.Cliente;
import br.com.importacaoarquivo.service.interfaces.ProcessaLinha;
import org.springframework.stereotype.Component;

@Component
public class ClienteProcessor implements ProcessaLinha {

	@Override
	public Cliente processarLinha(String[] linha) {
		String cnpj = linha[CampoCliente.CNPJ.getId()];
		String nome = linha[CampoCliente.NOME.getId()];
		String areaNegocio = linha[CampoCliente.AREA_NEGOCIO.getId()];
		return Cliente.builder()
				.cnpj(cnpj)
				.nome(nome)
				.areaNegocio(areaNegocio)
				.build();
	}
}
