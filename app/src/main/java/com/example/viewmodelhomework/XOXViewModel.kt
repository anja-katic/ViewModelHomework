package com.example.viewmodelhomework.ui

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.viewmodelhomework.databinding.FragmentXoxBinding
import com.example.viewmodelhomework.XOXViewModel

class XOXFragment : Fragment() {

    private var _binding: FragmentXoxBinding? = null
    private val binding get() = _binding!!

    private val viewModel: XOXViewModel by viewModels()

    private lateinit var timer: CountDownTimer

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentXoxBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //  promene poslednjeg rezultata i ažuriranje
        viewModel.lastResult.observe(viewLifecycleOwner, Observer { result ->
            binding.lastResultText.text = result ?: "Nema rezultata"
        })

        // primer postavljanja selektovanog polja
        binding.someCellView.setOnClickListener {
            viewModel.setSelectedCell(1, 2)
        }

        // Primer osluškivanja selektovanog polja
        viewModel.selectedCell.observe(viewLifecycleOwner, Observer { cell ->
            cell?.let {
                Toast.makeText(requireContext(), "Izabrano polje: red ${cell.first}, kolona ${cell.second}", Toast.LENGTH_SHORT).show()
            }
        })

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

        binding.doneButton.setOnClickListener {
            val bundle = Bundle().apply {
                putString("gameName", "Iks-Oks igra")
                putString("result", viewModel.lastResult.value ?: "N/A")
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

