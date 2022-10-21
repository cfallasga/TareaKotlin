package com.tarea.viewModel

import android.app.Application
import androidx.lifecycle.*
import com.tarea.data.EstadoDatabase
import com.tarea.model.Estado
import com.tarea.repository.EstadoRepository
import kotlinx.coroutines.launch

class EstadoViewModel(application: Application) : AndroidViewModel(application){

    val getEstados : LiveData<List<Estado>>

    private val repository : EstadoRepository

    init {
        val lugarDao = EstadoDatabase.getDatabase(application).lugarDao()
        repository = EstadoRepository(lugarDao)
        getEstados = repository.getEstados
    }

    fun saveEstado(estado: Estado) {
        viewModelScope.launch { repository.saveEstado(estado) }
    }

    fun deleteEstado(estado: Estado){
        viewModelScope.launch { repository.deleteEstado(estado) }
    }

}