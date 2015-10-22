package com.projetos.leo.model;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;

import com.projetos.leo.model.DispositivoBluetooth;

import java.util.LinkedList;
import java.util.Set;

/**
 * Created by leo on 20/10/15.
 */
public class ListaDeDispositivosBluetooth {

    private LinkedList<DispositivoBluetooth> listaDispositivos;

    public ListaDeDispositivosBluetooth(){

        this.listaDispositivos = new LinkedList<>();
        Set<BluetoothDevice> dispositivos = this.getDevices();

        if(dispositivos != null){
            if (dispositivos.size() > 0) {
                for (BluetoothDevice dispositivo : dispositivos) {
                    String nome = dispositivo.getName();
                    String mac = dispositivo.getAddress();
                    this.listaDispositivos.add(new DispositivoBluetooth(mac, nome));

                }
            }
        }

    }

    public LinkedList<DispositivoBluetooth> getListaDispositivos() {
        return this.listaDispositivos;
    }

    private Set<BluetoothDevice> getDevices(){
        BluetoothAdapter meuBluetooth = BluetoothAdapter.getDefaultAdapter();
        if (meuBluetooth != null)
            return meuBluetooth.getBondedDevices();
        return null;
    }
}
