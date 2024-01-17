package com.cbocka.guessnumber.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.cbocka.guessnumber.R
import com.cbocka.guessnumber.databinding.FragmentConfigBinding
import com.cbocka.guessnumber.usecase.GuessNumberState
import com.cbocka.guessnumber.usecase.GuessNumberViewModel
import com.google.android.material.textfield.TextInputLayout

/**
 * @author Carlos Bocka
 * Clase correspondiente a la primera vista de la aplicación.
 * Se pide introducir al usuario su nombre y el número de intentos con los que quiere realizar el juego
 */
class ConfigFragment : Fragment() {

    private var _binding : FragmentConfigBinding? = null
    private val binding get() = _binding!!

    private val viewModel : GuessNumberViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentConfigBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewmodel = viewModel
        binding.lifecycleOwner = this

        binding.tiePlayerName.addTextChangedListener(GuessNumberTextWatcher(binding.tilPlayerName))
        binding.tieTries.addTextChangedListener(GuessNumberTextWatcher(binding.tilTries))

        viewModel.getState().observe(viewLifecycleOwner, Observer {
            when(it) {
                GuessNumberState.NameIsMandatoryError -> setNameIsMandatoryError()
                GuessNumberState.TriesAreMandatoryError -> setTriesAreMandatoryError()
                GuessNumberState.NumberFormatError -> setNumberFormatError()
                GuessNumberState.NotEnoughTries -> onNotEnoughTries()
                GuessNumberState.Completed -> {}
                else -> onSuccess()
            }
        })
    }

    private fun setNameIsMandatoryError() {
        binding.tilPlayerName.error = getString(R.string.tie_player_name_error)
        binding.tiePlayerName.requestFocus()
    }

    private fun setTriesAreMandatoryError() {
        binding.tilTries.error = getString(R.string.tie_tries_empty_error)
        binding.tieTries.requestFocus()
    }

    private fun setNumberFormatError() {
        binding.tilTries.error = getString(R.string.tie_tries_format_error)
        binding.tieTries.requestFocus()
    }

    private fun onNotEnoughTries() {
        binding.tilTries.error = getString(R.string.tie_tries_not_enough_error)
        binding.tieTries.requestFocus()
    }

    private fun onSuccess() {
        viewModel.getState().value = GuessNumberState.Completed
        findNavController().navigate(R.id.action_configFragment_to_playFragment)
    }

    inner class GuessNumberTextWatcher(private val til : TextInputLayout) : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            til.error = null
        }
    }
}