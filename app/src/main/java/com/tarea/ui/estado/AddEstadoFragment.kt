package com.tarea.ui.estado

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.tarea.R
import com.tarea.databinding.FragmentAddEstadoBinding
import com.tarea.model.Estado
import com.tarea.viewModel.EstadoViewModel

class AddEstadoFragment : Fragment() {

    private var _binding: FragmentAddEstadoBinding? = null
    private val binding get() = _binding!!
    private lateinit var estadoViewModel: EstadoViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
         estadoViewModel = ViewModelProvider(this).get(EstadoViewModel::class.java)

        _binding = FragmentAddEstadoBinding.inflate(inflater, container, false)

        binding.btUpdateEstado.setOnClickListener{
            addEstado()
        }
        return binding.root
    }

    private fun addEstado() {
        val nombre = binding.etNombre.text.toString()

        if(nombre.isNotEmpty())
        {
            val capital = binding.etCapital.text.toString()
            val poblacion = binding.etPoblacion.text.toString().toInt()
            val costas = binding.cbCostas.isChecked

            //objeto
            val estado = Estado(0,nombre, capital, poblacion, costas)

            estadoViewModel.saveEstado(estado)

            Toast.makeText(requireContext(),getString(R.string.msg_estado_added), Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_addEstadoFragment_to_nav_estado)
        }
        else
        {
            Toast.makeText(requireContext(),getString(R.string.msg_data), Toast.LENGTH_LONG).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}