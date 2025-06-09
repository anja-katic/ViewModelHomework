package com.example.viewmodelhomework

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.yourapp.databinding.FragmentTextDisplayBinding
import com.example.yourapp.viewmodel.TextDisplayViewModel

class TextDisplayFragment : Fragment() {

    private var _binding: FragmentTextDisplayBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TextDisplayViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTextDisplayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Poveži UI sa ViewModel-om
        binding.optionTextView.text = "Изабрана опција: ${viewModel.getSelectedOption()}"

        // Klik na dugme "Готово"
        binding.doneButton.setOnClickListener {
            val bundle = Bundle().apply {
                putString("gameName", "Приказ текста")
                putString("gameResult", "Изабрана опција: ${viewModel.getSelectedOption()}")
            }
            findNavController().navigate(
                R.id.action_textDisplayFragment_to_resultFragment,
                bundle
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
