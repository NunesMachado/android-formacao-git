package br.com.alura.viagens.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import br.com.alura.viagens.R;

public class ListaPacotesActivity extends AppCompatActivity {

    private static final String TITULO_LISTA_PACOTES = "Pacotes";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(TITULO_LISTA_PACOTES);
        setContentView(R.layout.activity_lista_pacotes);
    }
}
