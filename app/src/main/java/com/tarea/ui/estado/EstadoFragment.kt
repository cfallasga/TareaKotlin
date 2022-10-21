package com.tarea.ui.estado

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tarea.R
import com.tarea.adapter.EstadoAdapter
import com.tarea.databinding.FragmentEstadoBinding
import com.tarea.viewModel.EstadoViewModel

class EstadoFragment : Fragment() {

    private lateinit var estadoViewModel: EstadoViewModel
    private val binding get() = _binding!!
    private var _binding: FragmentEstadoBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        estadoViewModel = ViewModelProvider(this).get(EstadoViewModel::class.java)
        _binding = FragmentEstadoBinding.inflate(inflater, container, false)

        binding.btAddEstadoFlo.setOnClickListener{
            findNavController().navigate(R.id.action_nav_estado_to_addEstadoFragment)
        }
        //Activacion del RecyclerView
        val estadoAdapter = EstadoAdapter()
        val reciclador = binding.reciclador

        reciclador.adapter = estadoAdapter
        reciclador.layoutManager = LinearLayoutManager(requireContext())

        estadoViewModel.getEstados.observe(viewLifecycleOwner) { estados ->
            estadoAdapter.setData(estados)
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}