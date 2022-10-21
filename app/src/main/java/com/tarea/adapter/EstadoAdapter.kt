package com.tarea.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.tarea.databinding.EstadoFilaBinding
import com.tarea.model.Estado
import com.tarea.ui.estado.EstadoFragmentDirections

class EstadoAdapter : RecyclerView.Adapter<EstadoAdapter.EstadoViewHolder>()  {
    //la lista para presentar la informacion de los estados
    private var listaEstados = emptyList<Estado>()

    inner class EstadoViewHolder(private val itemBinding: EstadoFilaBinding) :
        RecyclerView.ViewHolder(itemBinding.root){
        fun bind(estado: Estado){
            itemBinding.tvNombre.text = estado.nombre
            itemBinding.tvCapital.text = estado.capital
            itemBinding.tvPoblacion.text = estado.poblacion.toString()
            itemBinding.cbCostas.isChecked = estado.costas
            //Update
            itemBinding.vistaFila.setOnClickListener{
                val accion = EstadoFragmentDirections.actionNavEstadoToUpdateEstadoFragment(estado)
                itemView.findNavController().navigate(accion)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EstadoViewHolder {
        //creo un elemento en memoria de una cajita vista_fila
        val itemBinding = EstadoFilaBinding.inflate(
            LayoutInflater.from(parent.context)
            ,parent,false)
        //retorno una cajita en memoria
        return  EstadoViewHolder(itemBinding)

    }

    override fun onBindViewHolder(holder: EstadoViewHolder, position: Int) {
        //Obtengo el objeto que debo dibujar en la fila del recyclerView que voy
        val estadoActual = listaEstados[position]

        holder.bind(estadoActual)
    }

    override fun getItemCount(): Int {
        return listaEstados.size //cantidad de elementos que tiene la lista
    }

    fun setData(estados: List<Estado>){
        this.listaEstados = estados
        notifyDataSetChanged()
    }
}