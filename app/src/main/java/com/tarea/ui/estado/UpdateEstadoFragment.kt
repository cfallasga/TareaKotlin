package com.tarea.ui.estado

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.tarea.R
import com.tarea.databinding.FragmentUpdateEstadoBinding
import com.tarea.model.Estado
import com.tarea.viewModel.EstadoViewModel

class UpdateEstadoFragment : Fragment() {

    private var _binding: FragmentUpdateEstadoBinding? = null
    private val binding get() = _binding!!
    private lateinit var estadoViewModel: EstadoViewModel

    private val args by navArgs<UpdateEstadoFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
         estadoViewModel = ViewModelProvider(this).get(EstadoViewModel::class.java)

        _binding = FragmentUpdateEstadoBinding.inflate(inflater, container, false)

        binding.etNombre.setText(args.estado.nombre)
        binding.etCapital.setText(args.estado.capital)
        binding.etPoblacion.setText(args.estado.poblacion.toString())
        binding.cbCostas.isChecked = args.estado.costas

        binding.btUpdateEstado.setOnClickListener{
            updateEstado()
        }

        binding.btDeleteEstado.setOnClickListener{
            deleteEstado()
        }
        return binding.root
    }

    private fun updateEstado() {
        val nombre = binding.etNombre.text.toString()

        if(nombre.isNotEmpty())
        {
            val capital = binding.etCapital.text.toString()
            val poblacion = binding.etPoblacion.text.toString().toInt()
            val costas = binding.cbCostas.isChecked

            val estado = Estado(args.estado.id,nombre, capital, poblacion, costas)

            estadoViewModel.saveEstado(estado)
            Toast.makeText(requireContext(),getString(R.string.msg_estado_update), Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateEstadoFragment_to_nav_estado)
        }
        else
        {
            Toast.makeText(requireContext(),getString(R.string.msg_data), Toast.LENGTH_LONG).show()
        }
    }

    private fun deleteEstado() {
        val builder = AlertDialog.Builder(requireContext())

        builder.setPositiveButton(getString(R.string.msg_si)) { _, _ ->
            estadoViewModel.deleteEstado(args.estado)
            Toast.makeText(requireContext(),getString(R.string.msg_estado_delete)+"${args.estado.nombre}!",
                Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateEstadoFragment_to_nav_estado)
        }

        builder.setTitle(getString(R.string.bt_delete_estado))
        builder.setMessage(getString(R.string.msg_seguro_borrado) + "${args.estado.nombre}")
        builder.setNegativeButton(getString(R.string.msg_no)) { _, _ ->}
        builder.create().show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}