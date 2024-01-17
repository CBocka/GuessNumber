package com.cbocka.guessnumber.usecase

import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cbocka.guessnumber.model.GuessNumberGame
import kotlinx.coroutines.launch
import kotlin.random.Random

class GuessNumberViewModel : ViewModel() {

    private var state = MutableLiveData<GuessNumberState>()

    var name = MutableLiveData<String>()
    var tries = MutableLiveData<String>()

    fun getState(): MutableLiveData<GuessNumberState> {
        return state
    }

    fun startGame() {
        viewModelScope.launch {
            when {
                TextUtils.isEmpty(name.value) -> state.value = GuessNumberState.NameIsMandatoryError
                TextUtils.isEmpty(tries.value) -> state.value = GuessNumberState.TriesAreMandatoryError
                tries.value!!.toIntOrNull() == null -> state.value = GuessNumberState.NumberFormatError
                tries.value.toString().toInt() <= 0 -> state.value = GuessNumberState.NotEnoughTries
                else -> {
                    val number = Random.nextInt(101)

                    GuessNumberGame.initialice(number, tries.value.toString().toInt(), name.value.toString(), 0)
                    state.value = GuessNumberState.Success
                }
            }
        }
    }

    companion object {
        const val TAG = "GAME_RESULT"
    }
}