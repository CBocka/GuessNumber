package com.cbocka.guessnumber.ui

import OneOptionDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.cbocka.guessnumber.R
import com.cbocka.guessnumber.databinding.FragmentPlayBinding
import com.cbocka.guessnumber.usecase.GuessNumberViewModel
import com.cbocka.guessnumber.usecase.TryNumberState
import com.cbocka.guessnumber.usecase.TryNumberViewModel
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.delay

/**
 * @author Carlos Bocka
 * Clase correspondiente a la vista de juego de la aplicación.
 * El usuario introduce un número para tratar de acertar el número secreto.
 */
class PlayFragment : Fragment() {

    private var _binding : FragmentPlayBinding? = null
    private val binding get() = _binding!!

    private val viewModel : TryNumberViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPlayBinding.inflate(inflater, container, false)
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

        binding.tieTry.addTextChangedListener(TryNumberTextWatcher(binding.tilTry))

        viewModel.getState().observe(viewLifecycleOwner, Observer {
            when(it) {
                TryNumberState.EmptyNumber -> onEmptyNumber()
                TryNumberState.WrongNumberFormat -> onWrongNumberFormat()
                TryNumberState.IsLower -> onLowerNumber()
                TryNumberState.IsBigger -> onBiggerNumber()
                TryNumberState.NoMoreTries -> onNoMoreTries()
                TryNumberState.Win -> onSuccess()
            }
        })
    }

    private fun onEmptyNumber() {
        binding.tilTry.error = getString(R.string.til_try_empty_error)
        binding.tieTry.requestFocus()
    }

    private fun onWrongNumberFormat() {
        binding.tilTry.error = getString(R.string.til_try_format_error)
        binding.tieTry.requestFocus()
    }

    private fun onLowerNumber() {
        val dialog = OneOptionDialog.newInstance(getString(R.string.dialog_title), getString(R.string.dialog_result_lower_text))
        dialog.show((context as AppCompatActivity).supportFragmentManager, OneOptionDialog.KEY)
    }

    private fun onBiggerNumber() {
        val dialog = OneOptionDialog.newInstance(getString(R.string.dialog_title), getString(R.string.dialog_result_bigger_text))
        dialog.show((context as AppCompatActivity).supportFragmentManager, OneOptionDialog.KEY)
    }

    private fun onNoMoreTries() {
        val bundle = Bundle()
        bundle.putBoolean(GuessNumberViewModel.TAG, false)
        findNavController().navigate(R.id.action_playFragment_to_endGameFragment, bundle)
    }

    private fun onSuccess() {
        val bundle = Bundle()
        bundle.putBoolean(GuessNumberViewModel.TAG, true)
        findNavController().navigate(R.id.action_playFragment_to_endGameFragment, bundle)
    }

    inner class TryNumberTextWatcher(private val til : TextInputLayout) : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            til.error = null
        }
    }
}