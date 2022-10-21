package com.tarea.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.tarea.model.Estado
@Dao
interface EstadoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addEstado(lugar: Estado) //suspend fun es como async

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun updateEstado(lugar: Estado)

    @Delete
    suspend fun deleteEstado(lugar: Estado)

    @Query("select * from Estado")
    fun getEstados() : LiveData<List<Estado>>
}