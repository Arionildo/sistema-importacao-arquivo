package br.com.importacaoarquivo.mocker;

public class VendaMocker {

    public static String[] criarUmaLinha() {
        return new String[]{"003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPedro"};
    }

    public static String[] criarDuasLinhas() {
        return new String[]{"003ç08ç[1-10-100,2-30-2.50,3-40-3.10]çPedro", "003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo"};
    }

}
