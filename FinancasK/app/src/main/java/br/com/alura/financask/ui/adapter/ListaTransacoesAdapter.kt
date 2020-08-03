package br.com.alura.financask.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import br.com.alura.financask.R
import br.com.alura.financask.extension.formataParaBrasileiro
import br.com.alura.financask.model.Transacao
import kotlinx.android.synthetic.main.transacao_item.view.*


class ListaTransacoesAdapter(transacoes : List<Transacao>,
                             contexto : Context) : BaseAdapter() {

    private val transacoes = transacoes;
    private val contexto = contexto;

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val viewCriada = LayoutInflater.from(contexto).inflate(R.layout.transacao_item, parent, false)

        val transacao = transacoes[position]
        viewCriada.transacao_valor.text = transacao.valor.toString()
        viewCriada.transacao_categoria.text = transacao.categoria
        viewCriada.transacao_data.text = transacao.data.formataParaBrasileiro()
        return viewCriada
    }

    override fun getItem(position: Int): Transacao {
        return transacoes[position]
    }

    override fun getItemId(position: Int): Long {
       return 0
    }

    override fun getCount(): Int {
        return transacoes.size
    }

}