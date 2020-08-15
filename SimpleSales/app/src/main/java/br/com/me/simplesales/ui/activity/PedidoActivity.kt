package br.com.me.simplesales.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import br.com.me.simplesales.R
import br.com.me.simplesales.model.Pedido
import br.com.me.simplesales.model.PedidoProduto
import br.com.me.simplesales.ui.ResumoView
import br.com.me.simplesales.ui.activity.ActivityConstants.ADICIONAR
import br.com.me.simplesales.ui.activity.ActivityConstants.CANCELAR
import br.com.me.simplesales.ui.activity.ActivityConstants.CHAVE_PEDIDO
import br.com.me.simplesales.ui.dialog.AdicionaProdutoDialog
import kotlinx.android.synthetic.main.activity_pedido.*
import kotlinx.android.synthetic.main.form_pedido_produto.view.*

class PedidoActivity : AppCompatActivity() {

    lateinit var pedido: Pedido

    val cachePedidoProduto: MutableList<PedidoProduto> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pedido)
        supportActionBar?.hide()

        inicializaPedido()
        configuraBotaoAdicionarProduto()

        configuraResumo()

        val listaPedidoProduto = lista_pedido_produto

    }

    private fun configuraResumo() {
        val resumoView = ResumoView(view = window.decorView, produtos = cachePedidoProduto)
        resumoView.atualiza()
    }

    private fun configuraBotaoAdicionarProduto() {
        adicionar_produto.setOnClickListener {
            AdicionaProdutoDialog(contexto = this, viewGroup = window.decorView as ViewGroup)
                .criaFormulario {
                    cachePedidoProduto.add(it)
                    configuraResumo()
                }
        }
    }


    private fun inicializaPedido() {
        intent.getParcelableExtra<Pedido>(CHAVE_PEDIDO)?.let {
            numero_pedido.text = it.numeropedido.toString().padStart(4, '0')
            this.pedido = it
        }
    }
}