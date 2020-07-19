package br.com.alura.agenda.asynctask;

import android.os.AsyncTask;

import br.com.alura.agenda.model.Telefone;

abstract class BaseAlunoComTelefoneTask extends AsyncTask<Void, Void, Void> {

    private final FinalizadaListener listener;

    BaseAlunoComTelefoneTask(FinalizadaListener listener) {
        this.listener = listener;
    }

    void vinculaAlunoComTelefone(Integer idAluno, Telefone... telefones) {
        for (Telefone telefone : telefones) {
            telefone.setIdAluno(idAluno);
        }
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        listener.quandoFinalizada();
    }

    public interface FinalizadaListener{
        void quandoFinalizada();
    }
}
