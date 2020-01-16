package br.com.alura.viagens.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.viagens.R;
import br.com.alura.viagens.model.Pacote;

public class ListaPacotesAdapter extends BaseAdapter {

   final private List<Pacote> pacotes;
    private Context contexto;

    public ListaPacotesAdapter(List<Pacote> pacotes, Context contexto) {
        this.pacotes = pacotes;
        this.contexto = contexto;
    }

    @Override
    public int getCount() {
        return pacotes.size();
    }

    @Override
    public Object getItem(int position) {
        return pacotes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View viewCriada = LayoutInflater.from(contexto).inflate(R.layout.item_pacote, parent, false);
        return viewCriada;
    }
}
