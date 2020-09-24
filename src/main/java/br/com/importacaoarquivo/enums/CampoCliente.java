package br.com.importacaoarquivo.enums;

public enum CampoCliente {
	CNPJ(1),
	NOME(2),
	AREA_NEGOCIO(3);

	private final int id;

	CampoCliente(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
}
