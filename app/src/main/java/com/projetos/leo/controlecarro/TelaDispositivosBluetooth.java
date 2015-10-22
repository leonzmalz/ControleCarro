package com.projetos.leo.controlecarro;

import android.app.ListActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.projetos.leo.model.DispositivoBluetooth;
import com.projetos.leo.adapter.DispositivoBluetoothAdapter;
import com.projetos.leo.model.ListaDeDispositivosBluetooth;

public class TelaDispositivosBluetooth extends ListActivity {

	public static final String DEVICE_BLUETOOTH = null;
	private ListaDeDispositivosBluetooth listaBluetooth;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		listaBluetooth = new ListaDeDispositivosBluetooth();
		DispositivoBluetoothAdapter adapter = new DispositivoBluetoothAdapter(
				this, listaBluetooth.getListaDispositivos());
		this.setListAdapter(adapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		DispositivoBluetooth dispositivo = (DispositivoBluetooth) this.getListAdapter().getItem(position);
		Intent retornaBluetooth = new Intent();
		retornaBluetooth.putExtra(DEVICE_BLUETOOTH, dispositivo);
		setResult(RESULT_OK, retornaBluetooth);
		finish();
	}

}
