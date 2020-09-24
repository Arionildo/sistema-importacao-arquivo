package br.com.importacaoarquivo.enums;

public enum CampoItemVenda {
	ID(0),
	QUANTIDADE(1),
	PRECO(2);

	private final int id;

	CampoItemVenda(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
}
