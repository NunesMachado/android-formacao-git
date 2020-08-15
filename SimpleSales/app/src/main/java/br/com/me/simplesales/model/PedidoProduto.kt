package br.com.me.simplesales.model

import java.math.BigDecimal

class PedidoProduto(
  val id: Long =0,
  val nome: String,
  val descricao: String,
  val valor: BigDecimal,
  val quantidade: Int,
  val idPedido: Long =0
)