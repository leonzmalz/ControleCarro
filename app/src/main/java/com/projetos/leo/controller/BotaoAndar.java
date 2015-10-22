package com.projetos.leo.controller;
import android.view.View;

import com.projetos.leo.model.Carro;
import com.projetos.leo.model.ControleCarro;
import com.projetos.leo.model.DispositivoConexao;

/**
 * Created by leo on 20/10/15.
 */
public class BotaoAndar implements View.OnClickListener {

    private ControleCarro controleCarro;
    private DispositivoConexao device;

    public BotaoAndar(ControleCarro controleCarro, DispositivoConexao device) {
        this.controleCarro = controleCarro;
        this.device = device;
    }

    @Override
    public void onClick(View v) {
        Carro carro = new Carro(device);
        carro.andar(this.controleCarro);
    }
}
