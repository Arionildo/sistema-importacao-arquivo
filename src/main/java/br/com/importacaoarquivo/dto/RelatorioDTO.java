package br.com.importacaoarquivo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class RelatorioDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer quantidadeVendedores;
	private Integer quantidadeClientes;
	private Long idVendaMaisCara;
	private String nomePiorVendedor;

	public String gerarRelatorio() {
		return "\nRelatório:\n\tQuantidade de Vendedores: " +
				quantidadeVendedores +
				"\n\tQuantidade de Clientes: " +
				quantidadeClientes +
				"\n\tId da Venda Mais Cara: " +
				idVendaMaisCara +
				"\n\tNome do Pior Vendedor: " +
				nomePiorVendedor;
	}

}
