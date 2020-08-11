package br.com.alura.technews.repository

class FalhaResource<T>(erro: String) : Resource<T>(dado = null, erro = erro)