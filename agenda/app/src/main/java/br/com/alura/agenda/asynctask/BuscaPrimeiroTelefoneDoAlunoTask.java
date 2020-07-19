package br.com.alura.agenda.asynctask;

import android.os.AsyncTask;
import android.widget.TextView;

import br.com.alura.agenda.database.dao.TelefoneDAO;
import br.com.alura.agenda.model.Telefone;

public class BuscaPrimeiroTelefoneDoAlunoTask extends AsyncTask<Void, Void, Telefone> {
    private final TelefoneDAO dao;
    private final Integer idAluno;
    private final PrimeiroTelefoneEncontradoListener listener;

    public BuscaPrimeiroTelefoneDoAlunoTask(TelefoneDAO dao, Integer idAluno, PrimeiroTelefoneEncontradoListener listener) {
        this.dao = dao;
        this.idAluno = idAluno;
        this.listener = listener;
    }

    @Override
    protected Telefone doInBackground(Void... voids) {
        return  dao.buscaPrimeiroTelefoneAluno(idAluno);
    }

    @Override
    protected void onPostExecute(Telefone primeiroTelefone) {
        super.onPostExecute(primeiroTelefone);
        listener.quandoEncontrado(primeiroTelefone);
    }

    public interface PrimeiroTelefoneEncontradoListener{
        void quandoEncontrado(Telefone telefoneEncontrado);
    }
}
