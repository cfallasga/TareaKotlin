package com.tarea.repository

import androidx.lifecycle.LiveData
import com.tarea.data.EstadoDao
import com.tarea.model.Estado

class EstadoRepository (private val estadoDao: EstadoDao) {

    suspend fun saveEstado(estado: Estado) {
        if(estado.id==0){ //es un lugar nuevo
            estadoDao.addEstado(estado)
        }
        else
        {
            estadoDao.updateEstado(estado)
        }

    }

    suspend fun deleteEstado(estado: Estado){
        estadoDao.deleteEstado(estado)
    }

    var getEstados: LiveData<List<Estado>> = estadoDao.getEstados()
}