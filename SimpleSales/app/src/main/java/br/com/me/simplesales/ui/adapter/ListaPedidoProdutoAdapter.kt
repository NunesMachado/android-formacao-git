package br.com.me.simplesales.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.me.simplesales.R
import br.com.me.simplesales.model.PedidoProduto
import kotlinx.android.synthetic.main.item_pedido_produto.view.*

class ListaPedidoProdutoAdapter(
    private val contexto: Context,
    private val produtos: MutableList<PedidoProduto> = mutableListOf(),
    var quandoItemClicado: (pedidoProduto: PedidoProduto) -> Unit = {}
) : RecyclerView.Adapter<ListaPedidoProdutoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewCriada = LayoutInflater
            .from(contexto)
            .inflate( R.layout.item_pedido_produto, parent,false)
        return ViewHolder(viewCriada)
    }

    override fun getItemCount(): Int = produtos.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pedidoProduto: PedidoProduto =  produtos[position]
        holder.vincula(pedidoProduto)
    }

    fun adiciona(produto: PedidoProduto) {
        produtos.add(produto)
        val position = produtos.indexOf(produto)
        notifyItemInserted(position)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private lateinit var pedidoProduto: PedidoProduto

        init {
            itemView.setOnClickListener {
                if (::pedidoProduto.isInitialized) {
                    quandoItemClicado(pedidoProduto)
                }
            }
        }

        fun vincula(pedidoProduto: PedidoProduto) {
            this.pedidoProduto = pedidoProduto
            itemView.item_quantidade_produto_valor.text = pedidoProduto.quantidade.toString()
            itemView.item_nome_produto.text = pedidoProduto.nome
            itemView.item_valor_produto.text = pedidoProduto.valor.toString()
            itemView.item_valor_total.text = pedidoProduto.total().toString()
        }
    }

}