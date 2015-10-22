package com.projetos.leo.model;

/**
 * Created by leo on 20/10/15.
 */
public class Carro {

    private DispositivoConexao device;

    public Carro(DispositivoConexao device) {
        this.device = device;
    }

    public void ligar() {
        this.device.enviarComando(ControleCarro.LIGAR);
    }

    public void andar(ControleCarro controle){
        this.device.enviarComando(controle);
    }

}
