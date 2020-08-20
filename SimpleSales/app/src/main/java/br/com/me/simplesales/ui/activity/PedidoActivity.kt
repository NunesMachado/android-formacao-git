package br.com.me.simplesales.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.me.simplesales.R
import br.com.me.simplesales.cache.CacheProduto
import br.com.me.simplesales.model.Pedido
import br.com.me.simplesales.model.PedidoProduto
import br.com.me.simplesales.ui.ResumoView
import br.com.me.simplesales.ui.activity.ActivityConstants.CHAVE_PEDIDO
import br.com.me.simplesales.ui.adapter.ListaPedidoProdutoAdapter
import br.com.me.simplesales.ui.dialog.AdicionaProdutoDialog
import kotlinx.android.synthetic.main.activity_pedido.*
import java.math.BigDecimal

class PedidoActivity : AppCompatActivity() {

    lateinit var pedido: Pedido

    val cacheProduto = CacheProduto

    val adapter by lazy {
        ListaPedidoProdutoAdapter(
            contexto = this,
            produtos = cacheProduto.values().toMutableList()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pedido)
        supportActionBar?.hide()

        inicializaPedido()
        configuraBotaoAdicionarProduto()
        configuraRecyclerView()
        configuraResumo()
    }

    private fun configuraRecyclerView() {
        val divisor = DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        lista_pedido_produto.addItemDecoration(divisor)
        lista_pedido_produto.adapter = adapter
    }

    override fun onBackPressed() {
        super.onBackPressed()
        cacheProduto.clear()
    }

    private fun configuraResumo() {
        val resumoView = ResumoView(view = window.decorView, produtos = cacheProduto.values())
        resumoView.atualiza()
    }

    private fun configuraBotaoAdicionarProduto() {
        adicionar_produto.setOnClickListener {
            AdicionaProdutoDialog(contexto = this, viewGroup = window.decorView as ViewGroup)
                .criaFormulario {
                    cacheProduto.add(it)
                    adapter.adiciona(it)
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