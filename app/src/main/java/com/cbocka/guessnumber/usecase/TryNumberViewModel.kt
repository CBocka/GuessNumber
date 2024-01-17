package com.cbocka.guessnumber.usecase

import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cbocka.guessnumber.model.GuessNumberGame
import kotlinx.coroutines.launch

class TryNumberViewModel : ViewModel() {

    private var state = MutableLiveData<TryNumberState>()

    var tries = MutableLiveData(GuessNumberGame.instance.tries)
    var numberTry = MutableLiveData<String>()

    fun getState(): MutableLiveData<TryNumberState> {
        return state
    }

    fun validateNumber() {

            when {
                TextUtils.isEmpty(numberTry.value) -> state.value = TryNumberState.EmptyNumber
                else -> {
                    try {

                        val number = numberTry.value.toString().toInt()

                        if (number < 0 || number > 100)
                            state.value = TryNumberState.WrongNumberFormat
                        else {
                            tries.value = tries.value!! - 1
                            when {
                                number == GuessNumberGame.instance.number -> {
                                    state.value = TryNumberState.Win
                                }

                                tries.value == 0 -> state.value = TryNumberState.NoMoreTries

                                number < GuessNumberGame.instance.number -> {
                                    state.value = TryNumberState.IsBigger
                                }

                                number > GuessNumberGame.instance.number -> {
                                    state.value = TryNumberState.IsLower
                                }
                            }
                        }

                    } catch (ex: Exception) {
                        state.value = TryNumberState.WrongNumberFormat
                    }
                }

        }
    }
}