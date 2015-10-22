package com.projetos.leo.controller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.projetos.leo.model.DispositivoConexao;

import at.abraxas.amarino.AmarinoIntent;

public class StatusAmarino extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {

		final String action = intent.getAction();

		if (AmarinoIntent.ACTION_CONNECTED.equals(action)) {
			Toast.makeText(context, "Amarino  conectado",
					Toast.LENGTH_LONG).show();
		} else if (AmarinoIntent.ACTION_CONNECTION_FAILED.equals(action)) {
			Toast.makeText(context,
					"Erro durante a conexão ao amarino",
					Toast.LENGTH_LONG).show();
		}

		context.unregisterReceiver(this);
	}

}
