package br.com.alura.viagens.ui.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import br.com.alura.viagens.R;
import br.com.alura.viagens.model.Pacote;
import br.com.alura.viagens.ui.util.DataUtil;
import br.com.alura.viagens.ui.util.DiasUtl;
import br.com.alura.viagens.ui.util.MoedaUtil;
import br.com.alura.viagens.ui.util.ResourceUtil;

public class ResumoPacoteActivity extends AppCompatActivity {

    public static final String TITULO_APP_BAR = "Resumo do pacote";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumo_pacote);

        setTitle(TITULO_APP_BAR);

        Pacote pacote = new Pacote("São Paulo", "sao_paulo_sp", 2, new BigDecimal("243.99"));

        mostraLocal(pacote);

        mostraImagem(pacote);

        mostraDias(pacote);

        mostraPreco(pacote);

        mostraData(pacote);

    }

    private void mostraData(Pacote pacote) {
        TextView data = findViewById(R.id.resumo_pacote_data);
        String dataFormatadaDaViagem = DataUtil.periodoEmTexto(pacote.getDias());

        data.setText(dataFormatadaDaViagem);
    }



    private void mostraPreco(Pacote pacote) {
        TextView preco = findViewById(R.id.resumo_pacote_preco);
        String moedaBrasileira = MoedaUtil.formataParaBrasileiro(pacote.getPreco());
        preco.setText(moedaBrasileira);
    }

    private void mostraDias(Pacote pacote) {
        TextView dias = findViewById(R.id.resumo_pacote_dias);
        String diasEmTexto = DiasUtl.formataEmTexto(pacote.getDias());
        dias.setText(diasEmTexto);
    }

    private void mostraImagem(Pacote pacote) {
        ImageView imagem = findViewById(R.id.resumo_pacote_imagem);
        Drawable drawableDoPacote = ResourceUtil.devolveDrawable(this, pacote.getImagem());
        imagem.setImageDrawable(drawableDoPacote);
    }

    private void mostraLocal(Pacote pacote) {
        TextView local = findViewById(R.id.resumo_pacote_local);
        local.setText(pacote.getLocal());
    }
}