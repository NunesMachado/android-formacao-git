package br.com.alura.financask.model

import android.content.Context
import androidx.core.content.ContextCompat
import br.com.alura.financask.R
import java.math.BigDecimal

class Resumo(private val transacoes: List<Transacao>) {

    val receita get() = somaPor(Tipo.RECEITA)

    val despesa get() = somaPor(Tipo.DESPESA)

    val total get() = receita.subtract(despesa)

    fun somaPor(tipo: Tipo) : BigDecimal{
        val somaDeTransacoesPeloTipo = transacoes
            .filter { it.tipo == tipo }
            .sumByDouble { it.valor.toDouble() }
        return BigDecimal(somaDeTransacoesPeloTipo)
    }
}