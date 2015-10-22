package com.projetos.leo.model;

/**
 * Created by leo on 20/10/15.
 */
public enum OperacaoDispositivo {
    SOLICITA_CONEXAO(1),
    SOLICITA_ATIVACAO(2);

    private int valor;

    OperacaoDispositivo(int valor){
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}
