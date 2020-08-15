package br.com.me.simplesales.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.me.simplesales.R
import br.com.me.simplesales.database.AppDatabase
import br.com.me.simplesales.database.dao.UsuarioDAO
import br.com.me.simplesales.repository.UsuarioRepository
import br.com.me.simplesales.ui.activity.ActivityConstants.CHAVE_USUARIO
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private val repository: UsuarioRepository by lazy {
        UsuarioRepository(AppDatabase.getInstance(this).usuarioDAO)
    }

    private val campoLogin: EditText by lazy {
        activity_tela_inicial_login
    }

    private val campoSenha: EditText by lazy {
        activity_telainicial_senha
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()
        adicionaAdm()
        configuraBotaoEntrar()
    }

    private fun adicionaAdm() {
        repository.criaUsuarioAdm()
    }

    private fun configuraBotaoEntrar() {
        val botaoEntrar = activity_telainicial_botao_entrar
        botaoEntrar.setOnClickListener {
            val login = campoLogin.text.toString()
            val senha = campoSenha.text.toString()

            repository.buscaUsuarioPorLoginESenha(login, senha) {
                if(it != null){
                    val vaiParaTelaPrincipalActivity = Intent(this, TelaPrincipalActivity::class.java)
                    vaiParaTelaPrincipalActivity.putExtra(CHAVE_USUARIO, it)
                    startActivity(vaiParaTelaPrincipalActivity)
                }else {
                    Toast.makeText(
                        this@LoginActivity,
                        "Senha e usuario invalido",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}