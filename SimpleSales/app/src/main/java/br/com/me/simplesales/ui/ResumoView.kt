package br.com.me.simplesales.ui

import android.content.Context
import android.view.View
import br.com.me.simplesales.model.PedidoProduto
import br.com.me.simplesales.model.Resumo
import kotlinx.android.synthetic.main.activity_pedido.view.*

class ResumoView(private val view: View,
                 produtos: List<PedidoProduto>
                 ) {
    private val resumo: Resumo = Resumo(produtos)

    fun atualiza() {
        with(resumo){
            view.qtd_produto_valor.text = getQtdProduto().toString()
            view.qtd_item_valor.text = getQtdItens().toString()
            view.valor_of_valor_total_pedido.text = getValorTotalPedido().toString()
        }
    }
}