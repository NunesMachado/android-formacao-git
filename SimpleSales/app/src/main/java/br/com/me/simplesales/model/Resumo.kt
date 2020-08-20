package br.com.me.simplesales.model


import java.math.BigDecimal

class Resumo(val cacheProdutos: List<PedidoProduto>) {

    fun getValorTotalPedido(): BigDecimal {
        val soma = cacheProdutos.sumByDouble {
            it.total().toDouble()
        }
        return BigDecimal(soma)
    }

    fun getQtdItens(): Int{
        return cacheProdutos.sumBy {
            it.quantidade
        }
    }
    fun getQtdProduto(): Int {
        return cacheProdutos.size
    }
}