package com.projetos.leo.model;

import android.content.Context;

/**
 * Created by leo on 20/10/15.
 */
public interface DispositivoConexao {

    public void conectar(Context ctx);
    public void desconectar();
    public void enviarComando(ControleCarro controle);

}
