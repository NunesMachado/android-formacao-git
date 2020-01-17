package br.com.alura.viagens.ui.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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

        Pacote pacote = pacotes.get(position);

        TextView local = viewCriada.findViewById(R.id.item_pacote_local);
        local.setText(pacote.getLocal());

        ImageView imagem = viewCriada.findViewById(R.id.item_pacote_imagem);
        Resources resources = contexto.getResources();
        int iddrawable = resources.getIdentifier(pacote.getImagem(), "drawable", contexto.getPackageName());
        Drawable drawable = resources.getDrawable(iddrawable);
        imagem.setImageDrawable(drawable);

        TextView dias = viewCriada.findViewById(R.id.item_pacote_dias);
        String diasEmTexto = "";
        int diasDoPacote = pacote.getDias();

        diasEmTexto = diasDoPacote > 1 ? diasDoPacote + " dias" : diasDoPacote + " dia";

        dias.setText(diasEmTexto);

        TextView preco = viewCriada.findViewById(R.id.item_pacote_preco);
        BigDecimal precoPacote = pacote.getPreco();
        NumberFormat formatoBrasileiro = DecimalFormat
                .getCurrencyInstance(new Locale("pt","br"));
        String moedaBrasileira = formatoBrasileiro
                .format(precoPacote)
                .replace("R$", "R$ ");
        preco.setText(moedaBrasileira);


        return viewCriada;
    }
}
