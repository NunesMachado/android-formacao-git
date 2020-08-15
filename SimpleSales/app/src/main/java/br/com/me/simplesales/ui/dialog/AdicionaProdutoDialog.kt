package br.com.me.simplesales.ui.dialog

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import br.com.me.simplesales.R
import br.com.me.simplesales.model.PedidoProduto
import br.com.me.simplesales.ui.activity.ActivityConstants.ADICIONAR
import br.com.me.simplesales.ui.activity.ActivityConstants.CANCELAR
import kotlinx.android.synthetic.main.form_pedido_produto.view.*

class AdicionaProdutoDialog(private val contexto: Context,
                            private val viewGroup: ViewGroup) {

val viewCriada = criaViewFormularioProduto()

fun criaFormulario(delegate: (pedidoproduto: PedidoProduto) -> Unit){
    AlertDialog.Builder(contexto)
        .setTitle("Produto")
        .setView(viewCriada)
        .setPositiveButton(ADICIONAR) { _, _ ->

            val nomeProduto = viewCriada.campo_nome_produto.text.toString()
            val descricaoProduto = viewCriada.campo_descricaoProduto.text.toString()
            val quantidadeProduto = viewCriada.campo_quantidade.text.toString()
            val valorProduto = viewCriada.campo_valor.text.toString()

            val pedidoProduto = PedidoProduto(
                nome = nomeProduto,
                descricao = descricaoProduto,
                quantidade = quantidadeProduto.toInt(),
                valor = valorProduto.toBigDecimal()
            )
            delegate(pedidoProduto)
        }
        .setNegativeButton(CANCELAR, null)
        .show()

}

    private fun criaViewFormularioProduto(): View {
        return LayoutInflater.from(contexto)
            .inflate(
                R.layout.form_pedido_produto,
                viewGroup,
                false
            )
    }
}


