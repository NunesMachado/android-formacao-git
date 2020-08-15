package br.com.me.simplesales.ui.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.me.simplesales.model.PedidoProduto

class ListaPedidoProdutoAdapter(
    private val contexto: Context,
    private val produtos: MutableList<PedidoProduto> = mutableListOf(),
    var quandoItemClicado:(pedidoProduto: PedidoProduto) -> Unit ={}
) : RecyclerView.Adapter<ListaPedidoProdutoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private lateinit var pedidoProduto: PedidoProduto

        init {
            itemView.setOnClickListener {
                if (::pedidoProduto.isInitialized) {

                }
            }
        }
    }

}