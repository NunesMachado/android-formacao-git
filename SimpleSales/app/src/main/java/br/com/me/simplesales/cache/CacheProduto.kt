package br.com.me.simplesales.cache

import br.com.me.simplesales.model.PedidoProduto

object CacheProduto : ICache<PedidoProduto> {

    val cacheProduto = mutableListOf<PedidoProduto>()
    override fun add(value: PedidoProduto) {
        cacheProduto.add(value)
    }

    override fun remove(value: PedidoProduto) {
        cacheProduto.remove(value)
    }

    override fun get(value: PedidoProduto): PedidoProduto? {
        return cacheProduto[cacheProduto.indexOf(value)]
    }

    override fun clear() {
        cacheProduto.clear()
    }

    override fun size(): Int {
        return cacheProduto.size
    }

    override fun values(): List<PedidoProduto> {
        return cacheProduto.toList()
    }
}