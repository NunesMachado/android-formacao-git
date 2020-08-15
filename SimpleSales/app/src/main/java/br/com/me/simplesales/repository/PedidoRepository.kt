package br.com.me.simplesales.repository

import br.com.me.simplesales.asynctask.BaseAsyncTask
import br.com.me.simplesales.database.dao.PedidoDAO

class PedidoRepository(private val dao : PedidoDAO) {

    fun getNovoNumero(
        quandofinaliza: (result: Long?) -> Unit
    ) {
        BaseAsyncTask(quandoExecuta = {
            dao.getNovoNumero()
        },quandoFinaliza =quandofinaliza ).execute()
    }
}