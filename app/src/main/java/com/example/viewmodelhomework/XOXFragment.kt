package com.example.viewmodelhomework.ui

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.viewmodelhomework.databinding.FragmentXoxBinding
import com.example.viewmodelhomework.viewmodel.XOXViewModel

class XOXFragment : Fragment() {

    private var _binding: FragmentXoxBinding? = null
    private val binding get() = _binding!!

    // Inicijalizacija ViewModel-a
    private val viewModel: XOXViewModel by viewModels()

    private lateinit var timer: CountDownTimer

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentXoxBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // prikaz trenutnog rezultata iz ViewModel-a (ako postoji)
        binding.lastResultText.text = viewModel.getLastResult()

        // Startuj CountDownTimer od 10 sekundi sa intervalom 1s
        timer = object : CountDownTimer(10_000, 1_000) {
            override fun onTick(millisUntilFinished: Long) {
                binding.timerText.text = "Preostalo vreme: ${millisUntilFinished / 1000} s"
            }

            override fun onFinish() {
                Toast.makeText(requireContext(), "Vreme je isteklo!", Toast.LENGTH_SHORT).show()
                binding.timerText.text = "Vreme je isteklo!"
            }
        }
        timer.start()

        // Dugme "Готово" koje otvara ResultFragment sa podacima iz ViewModel-a
        binding.doneButton.setOnClickListener {
            val bundle = Bundle().apply {
                putString("gameName", "Iks-Oks igra")
                putString("result", viewModel.getLastResult())
            }

            val resultFragment = ResultFragment()
            resultFragment.arguments = bundle

            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, resultFragment)
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        timer.cancel()
        _binding = null
    }
}

