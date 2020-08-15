package br.com.me.simplesales.database.dao

import androidx.room.Dao
import androidx.room.Query
@Dao
interface PedidoDAO {
    @Query("SELECT max(numeropedido) + 1 FROM Pedido")
    fun getNovoNumero(): Long?
}