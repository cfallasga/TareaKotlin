package com.tarea.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tarea.model.Estado

@Database(entities = [Estado::class], version = 1, exportSchema = false)
abstract class EstadoDatabase  : RoomDatabase(){
    abstract fun lugarDao() : EstadoDao

    companion object {
        @Volatile
        private var INSTANCE: EstadoDatabase? = null

        fun getDatabase(context: android.content.Context) : EstadoDatabase {
            val temp = INSTANCE
            if (temp != null){
                return temp
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EstadoDatabase::class.java,
                    "estado_database"
                ).build()
                INSTANCE = instance
                return  instance
            }
        }
    }
}