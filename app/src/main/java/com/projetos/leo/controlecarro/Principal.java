package com.projetos.leo.controlecarro;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.projetos.leo.controller.StatusAmarino;
import com.projetos.leo.controller.BotaoAndar;
import com.projetos.leo.controller.BotaoLigar;
import com.projetos.leo.model.ControleCarro;
import com.projetos.leo.model.DispositivoConexao;
import com.projetos.leo.model.OperacaoDispositivo;
import com.projetos.leo.model.SituacaoBluetooth;

import at.abraxas.amarino.AmarinoIntent;

public class Principal extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Button btnLigar, btnBuzinar, btnFarois;
    private ImageButton btnFrente, btnRe, btnEsquerda, btnDireita, btnParar;
    private StatusAmarino statusAmarino;
    private DispositivoConexao device;
    private SituacaoBluetooth situacaoBluetooth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        inicializarComponentes();
        ativarBluetooth();
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.principal, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(OperacaoDispositivo.SOLICITA_ATIVACAO.getValor() == requestCode)
            verificaAtivacao(resultCode == Activity.RESULT_OK);
        else
            iniciaConexao(resultCode == Activity.RESULT_OK, data);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_conecta:
                this.conectarDispositivoBluetooth();
            case R.id.nav_desconecta:
                this.desconectarDispositivo();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onStop() {
        this.desconectarDispositivo();
        super.onStop();
    }

    private void conectarDispositivoBluetooth() {
        if (situacaoBluetooth.equals(SituacaoBluetooth.ATIVO)) {
            Intent abreLista = new Intent(this,
                    TelaDispositivosBluetooth.class);
            startActivityForResult(abreLista, OperacaoDispositivo.SOLICITA_CONEXAO.getValor());

        } else
            Toast.makeText(this, "Não é possível conectar com o bluetooth desativado", Toast.LENGTH_SHORT).show();

    }

    private void desconectarDispositivo() {
        if (this.device != null) {
                this.device.desconectar();
        }

    }

    private boolean iniciaConexao(boolean conectou, Intent data) {
        if (conectou) {
            this.device = (DispositivoConexao) data.getExtras().getSerializable(TelaDispositivosBluetooth.DEVICE_BLUETOOTH);
            this.device.conectar(this);
            this.habilitarComunicacao();
            this.registrarEventos();
            return true;
        }
        Toast.makeText(this, "Falha ao obter o Dispositivo",
                Toast.LENGTH_SHORT).show();
        return false;

    }

    private void habilitarComunicacao() {
        this.statusAmarino = new StatusAmarino();
        registerReceiver(statusAmarino, new IntentFilter(
                AmarinoIntent.ACTION_CONNECTED));

    }

    private void ativarBluetooth() {
        Intent ativaBluetooth = new Intent(
                BluetoothAdapter.ACTION_REQUEST_ENABLE);
        startActivityForResult(ativaBluetooth, OperacaoDispositivo.SOLICITA_ATIVACAO.getValor());

    }

    private boolean verificaAtivacao(boolean ativou) {
        if (ativou) {
            this.situacaoBluetooth = SituacaoBluetooth.ATIVO;
            Toast.makeText(this, "Bluetooth Ativado", Toast.LENGTH_SHORT)
                    .show();
            return true;
        }

        Toast.makeText(this, "Bluetooth não foi ativado", Toast.LENGTH_SHORT)
                .show();
        return false;
    }

    private void registrarEventos() {
        this.btnLigar.setOnClickListener(new BotaoLigar(this.device));
        this.btnFrente.setOnClickListener(new BotaoAndar(ControleCarro.FRENTE, this.device));
        this.btnRe.setOnClickListener(new BotaoAndar(ControleCarro.RE, this.device));
        this.btnEsquerda.setOnClickListener(new BotaoAndar(ControleCarro.ESQUERDA, this.device));
        this.btnDireita.setOnClickListener(new BotaoAndar(ControleCarro.DIREITA, this.device));
    }

    private void inicializarComponentes() {
        this.btnLigar = (Button) findViewById(R.id.btnLigar);
        this.btnBuzinar = (Button) findViewById(R.id.btnBuzinar);
        this.btnDireita = (ImageButton) findViewById(R.id.btnDireita);
        this.btnEsquerda = (ImageButton) findViewById(R.id.btnEsquerda);
        this.btnFarois = (Button) findViewById(R.id.btnFarol);
        this.btnFrente = (ImageButton) findViewById(R.id.btnFrente);
        this.btnParar = (ImageButton) findViewById(R.id.btnParar);
        this.btnRe = (ImageButton) findViewById(R.id.btnRe);

    }
}
