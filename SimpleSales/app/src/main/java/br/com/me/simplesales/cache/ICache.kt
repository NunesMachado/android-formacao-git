package br.com.me.simplesales.cache

interface ICache<T> {

    fun add(value: T)

    fun remove(value: T)

    fun get(value: T): T?

    fun clear()

    fun size(): Int

    fun values(): List<T>
}