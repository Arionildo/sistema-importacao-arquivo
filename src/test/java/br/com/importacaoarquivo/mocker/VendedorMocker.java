package br.com.importacaoarquivo.mocker;

public class VendedorMocker {

    public static String[] criarUmaLinha() {
        return new String[]{"001ç1234567891234çPedroç40000.99"};
    }

    public static String[] criarDuasLinhas() {
        return new String[]{"001ç1234567891234çPedroç40000.99", "001ç3245678865434çPauloç40000.99"};
    }
}
