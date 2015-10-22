package com.projetos.leo.model;

/**
 * Created by leo on 20/10/15.
 */
public enum ControleCarro {
    LIGAR(1,'L'),
    FRENTE(2,'F'),
    RE(3,'R'),
    ESQUERDA(4,'E'),
    DIREITA(5,'D'),
    PARAR(6,'P');

    int valor;
    char parametro;

    ControleCarro(int valor, char parametro) {
        this.valor = valor;
        this.parametro = parametro;
    }

    public int getValor() {
        return valor;
    }

    public char getParametro() {
        return parametro;
    }
}
