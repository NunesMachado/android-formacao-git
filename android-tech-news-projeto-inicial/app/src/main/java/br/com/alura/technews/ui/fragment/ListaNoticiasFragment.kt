package br.com.alura.technews.ui.fragment

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.alura.technews.database.AppDatabase
import br.com.alura.technews.repository.NoticiaRepository
import br.com.alura.technews.ui.fragment.extensions.mostraErro
import br.com.alura.technews.ui.recyclerview.adapter.ListaNoticiasAdapter
import br.com.alura.technews.ui.viewmodel.ListaNoticiasViewModel
import br.com.alura.technews.ui.viewmodel.factory.ListaNoticiasViewModelFactory
import kotlinx.android.synthetic.main.lista_noticias.*


private const val MENSAGEM_FALHA_CARREGAR_NOTICIAS = "Não foi possível carregar as novas notícias"

class ListaNoticiasFragment : Fragment() {

    private val viewModel by lazy {
        val repository = NoticiaRepository(AppDatabase.getInstance(this).noticiaDAO)
        val factory = ListaNoticiasViewModelFactory(repository)
        val provedor = ViewModelProviders.of(this, factory)
        provedor.get(ListaNoticiasViewModel::class.java)
    }

    private val adapter by lazy {
        context?.let {
            ListaNoticiasAdapter(context = it)
        }?: throw  IllegalArgumentException("Contexto invalido!")
    }

    private fun configuraFabAdicionaNoticia() {
        lista_noticias_fab_salva_noticia.setOnClickListener {
        //    abreFormularioModoCriacao()
        }
    }

    private fun configuraRecyclerView() {
        val divisor = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
        lista_noticias_recyclerview.addItemDecoration(divisor)
        lista_noticias_recyclerview.adapter = adapter
        configuraAdapter()
    }

    private fun configuraAdapter() {
        //adapter.quandoItemClicado = this::abreVisualizadorNoticia
    }

    private fun buscaNoticias() {
        viewModel.buscaTodos().observe(this, Observer { resource ->
            resource.dado?.let { adapter.atualiza(it) }
            resource.erro?.let {
                mostraErro(MENSAGEM_FALHA_CARREGAR_NOTICIAS)
            }
        })
    }
}