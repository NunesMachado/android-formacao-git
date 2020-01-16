package br.com.alura.viagens.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

import br.com.alura.viagens.R;
import br.com.alura.viagens.dao.PacoteDAO;
import br.com.alura.viagens.model.Pacote;
import br.com.alura.viagens.ui.adapter.ListaPacotesAdapter;

public class ListaPacotesActivity extends AppCompatActivity {

    private static final String TITULO_LISTA_PACOTES = "Pacotes";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(TITULO_LISTA_PACOTES);
        setContentView(R.layout.activity_lista_pacotes);

        List<Pacote> listaPacotes = new PacoteDAO().lista();

        ListView listaDePacotes = findViewById(R.id.lista_pacotes_listview);
        listaDePacotes.setAdapter(new ListaPacotesAdapter(listaPacotes, this));
    }
}
