package br.com.alura.financask.ui.dialog

import android.content.Context
import android.view.ViewGroup
import br.com.alura.financask.R
import br.com.alura.financask.model.Tipo

class AdicionaTransacaoDialog(
    viewGroup: ViewGroup,
    contexto: Context
) : FormularioTransacaoDialog(contexto, viewGroup) {

    override val tituloBotaoPositivo: String
        get() ="Adcionar"

    override fun tituloPor(tipo: Tipo): Int {
        if (tipo == Tipo.RECEITA) {
            return R.string.adiciona_receita
        }
        return R.string.adiciona_despesa
    }
}