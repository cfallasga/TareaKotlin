package com.tarea.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "estado")
data class Estado (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "nombre")
    var nombre: String,
    @ColumnInfo(name = "capital")
    var capital: String,
    @ColumnInfo(name = "poblacion")
    var poblacion: Int,
    @ColumnInfo(name = "costas")
    var costas: Boolean
):Parcelable