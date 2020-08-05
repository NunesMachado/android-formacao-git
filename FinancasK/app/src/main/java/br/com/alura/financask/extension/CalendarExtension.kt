package br.com.alura.financask.extension

import java.text.SimpleDateFormat
import java.util.*


fun Calendar.formataParaBrasileiro() : String{
    val formatoBrasileiro = "dd/MM/yyyy"
    val simpleformat = SimpleDateFormat(formatoBrasileiro)
    return simpleformat.format(this.time)
}