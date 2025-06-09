package com.example.viewmodelhomework

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.viewmodelhomework.databinding.FragmentResultBinding

class ResultFragment : Fragment() {

    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ResultViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val gameName = arguments?.getString("gameName") ?: "Nepoznata igra"
        val gameResult = arguments?.getString("gameResult") ?: "Nema rezultata"

        val factory = ResultViewModelFactory(gameName, gameResult)
        viewModel = ViewModelProvider(this, factory)[ResultViewModel::class.java]

        binding.gameName.text = viewModel.gameName
        binding.gameResult.text = viewModel.gameResult
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
