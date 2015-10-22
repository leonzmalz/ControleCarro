package com.projetos.leo.model;

import android.content.Context;

import java.io.Serializable;

import at.abraxas.amarino.Amarino;

public class DispositivoBluetooth implements Serializable, DispositivoConexao {

    private SituacaoBluetooth status;
    private String mac;
    private String nome;
    private Context ctx;

    public DispositivoBluetooth(String mac, String nome) {
        status = SituacaoBluetooth.DESCONECTADO;
        this.mac = mac;
        this.nome = nome;
    }

    public void conectar(Context ctx) {
        if (status.equals(SituacaoBluetooth.DESCONECTADO)) {
            this.ctx = ctx;
            Amarino.connect(ctx, this.mac);
            this.status = SituacaoBluetooth.CONECTADO;
        }

    }

    public void desconectar() {
        if (status.equals(SituacaoBluetooth.CONECTADO)) {
            Amarino.disconnect(this.ctx, this.mac);
            this.status = SituacaoBluetooth.DESCONECTADO;
        }
    }

    @Override
    public void enviarComando(ControleCarro controle) {
        if (status.equals(SituacaoBluetooth.CONECTADO)) {
            char parametro = controle.getParametro();
            int valor = controle.getValor();
            Amarino.sendDataToArduino(this.ctx, this.mac, parametro, valor);
        }
    }

    public String getNome() {
        return nome;
    }

    public String getMac() {
        return mac;
    }

}
