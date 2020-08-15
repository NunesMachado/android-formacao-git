package br.com.me.simplesales.repository

import br.com.me.simplesales.asynctask.BaseAsyncTask
import br.com.me.simplesales.database.dao.UsuarioDAO
import br.com.me.simplesales.model.Usuario

class UsuarioRepository(private val dao: UsuarioDAO) {

    fun criaUsuarioAdm() {
        BaseAsyncTask(quandoExecuta = {
            dao.buscaUsuarioPorLoginESenha("admin", "123")
        }, quandoFinaliza = {
            if (it == null) {
                dao.salva(Usuario(login = "admin", senha = "123"))
            }
        }).execute()
    }

    fun buscaUsuarioPorLoginESenha(
        login: String,
        senha: String,
        quandoFinaliza: (result: Usuario?) -> Unit
    ) {
        BaseAsyncTask(quandoExecuta = {
            dao.buscaUsuarioPorLoginESenha(login, senha)
        }, quandoFinaliza = quandoFinaliza).execute()
    }

}