package br.com.alura.financask.ui.dialog

import android.content.Context
import android.view.ViewGroup
import br.com.alura.financask.R
import br.com.alura.financask.extension.formataParaBrasileiro
import br.com.alura.financask.model.Tipo
import br.com.alura.financask.model.Transacao

class AlteraTransacaoDialog(
    viewGroup: ViewGroup,
    private val contexto: Context
) : FormularioTransacaoDialog(contexto, viewGroup) {

    override val tituloBotaoPositivo: String
        get() = "Alterar"

    override fun tituloPor(tipo: Tipo): Int {
        if (tipo == Tipo.RECEITA) {
            return R.string.altera_receita
        }
        return R.string.altera_despesa
    }

    fun chama(
        transacao: Transacao,
        delegate: (transacao: Transacao)->Unit
    ) {
        val tipo = transacao.tipo

        super.chama(tipo, delegate)

        inicializaCampos(transacao)
    }

    private fun inicializaCampos(
        transacao: Transacao
    ) {
        inicializaCampoValor(transacao)
        incializaCampoData(transacao)
        inicializaCampoCategoria(transacao)
    }

    private fun inicializaCampoCategoria(
        transacao: Transacao
    ) {
        var categoriasRetornadas = contexto.resources.getStringArray(categoriaPor(transacao.tipo))
        val posicaoCategoria = categoriasRetornadas.indexOf(transacao.categoria)
        campoCategoria.setSelection(posicaoCategoria, true)
    }

    private fun incializaCampoData(transacao: Transacao) {
        campoData.setText(transacao.data.formataParaBrasileiro())
    }

    private fun inicializaCampoValor(transacao: Transacao) {
        campoValor.setText(transacao.valor.toString())
    }


}