package br.com.alura.viagens.ui.util;

public class DiasUtl {

    public static final String PLURAL = " dias";
    public static final String SINGULAR = " dia";

    public static String formataEmTexto(int quantidadesDeDias) {
       return quantidadesDeDias > 1 ? quantidadesDeDias + PLURAL : quantidadesDeDias + SINGULAR;
    }

}
