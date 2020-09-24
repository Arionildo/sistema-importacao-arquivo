package br.com.importacaoarquivo.enums;

public enum CampoVenda {
	ID(1),
	ITEM(2),
	VENDEDOR(3);

	private final int id;

	CampoVenda(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
}
