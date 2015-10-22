package com.projetos.leo.controller;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.projetos.leo.model.Carro;
import com.projetos.leo.model.DispositivoConexao;

/**
 * Created by leo on 20/10/15.
 */
public class BotaoLigar implements View.OnClickListener {

    private DispositivoConexao device;

    public BotaoLigar(DispositivoConexao device) {
        this.device = device;
    }

    @Override
    public void onClick(View v) {
        Carro carro = new Carro(this.device);
        carro.ligar();
    }
}
