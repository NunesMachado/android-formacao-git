package br.com.me.simplesales.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.me.simplesales.R
import br.com.me.simplesales.database.AppDatabase
import br.com.me.simplesales.model.Pedido
import br.com.me.simplesales.model.Usuario
import br.com.me.simplesales.repository.PedidoRepository
import br.com.me.simplesales.ui.activity.ActivityConstants.CHAVE_PEDIDO
import br.com.me.simplesales.ui.activity.ActivityConstants.CHAVE_USUARIO
import kotlinx.android.synthetic.main.activity_tela_principal.*

class TelaPrincipalActivity : AppCompatActivity() {

    val repository: PedidoRepository by lazy {
        PedidoRepository(AppDatabase.getInstance(this).pedidoDAO)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_principal)
        supportActionBar?.hide()
        configuraBotaoIniciaPedido()
    }

    private fun configuraBotaoIniciaPedido() {
        botao_novo_pedido.setOnClickListener {
            val abreCadastroPedido = Intent(this, PedidoActivity::class.java)
            val pedido = Pedido(numeropedido = getNovoNumeroPedido(), usuarioid = getUsuarioId())
            abreCadastroPedido.putExtra(CHAVE_PEDIDO, pedido)
            startActivity(abreCadastroPedido)
        }
    }

    private fun getNovoNumeroPedido(): Long {
        var novoNumero: Long = 1
        repository.getNovoNumero {
            it?.let {
                novoNumero = it
            }
        }
        return novoNumero
    }

    private fun getUsuarioId(): Long {
        val usuario = intent.getParcelableExtra<Usuario>(CHAVE_USUARIO)
        if(usuario != null) return  usuario.id
        return 0
    }
}