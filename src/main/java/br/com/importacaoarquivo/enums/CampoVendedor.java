package br.com.importacaoarquivo.enums;

public enum CampoVendedor {
	CPF(1),
	NOME(2),
	SALARIO(3);

	private final int id;

	CampoVendedor(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
}
