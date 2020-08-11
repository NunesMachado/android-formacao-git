package br.com.alura.technews.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import br.com.alura.technews.model.Noticia
import br.com.alura.technews.repository.NoticiaRepository
import br.com.alura.technews.repository.Resource

class FormularioNoticiaViewModel(private val REPOSITORY: NoticiaRepository) : ViewModel() {
    fun salva(noticia: Noticia): LiveData<Resource<Void?>> {
        return if (noticia.id > 0) {
            REPOSITORY.edita(noticia = noticia)
        } else {
            REPOSITORY.salva(noticia = noticia)
        }
    }
}