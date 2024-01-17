package com.cbocka.guessnumber.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.cbocka.guessnumber.R
import com.cbocka.guessnumber.databinding.FragmentEndGameBinding
import com.cbocka.guessnumber.usecase.GuessNumberViewModel

/**
 * @author Carlos Bocka
 * Clase correspondiente a la vista del resultado de la partida.
 */
class EndGameFragment : Fragment() {

    private var _binding : FragmentEndGameBinding? = null
    private val binding get() = _binding!!

    private var gameResult : Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEndGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gameResult = requireArguments().getBoolean(GuessNumberViewModel.TAG)

        binding.btnPlayAgain.setOnClickListener {
            findNavController().navigate(R.id.action_endGameFragment_to_configFragment)
        }

        initializeView(gameResult)
    }

    private fun initializeView(result : Boolean) {
        if (result) {
            binding.animationView.setAnimation(R.raw.win)
            binding.tvResult.text = getString(R.string.tvResult_winning_text)
        } else {
            binding.animationView.setAnimation(R.raw.lose)
            binding.tvResult.text = getString(R.string.tvResult_losing_text)
        }
    }
}