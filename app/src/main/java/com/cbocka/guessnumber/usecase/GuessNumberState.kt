package com.cbocka.guessnumber.usecase

sealed class GuessNumberState() {
    data object NameIsMandatoryError : GuessNumberState()
    data object TriesAreMandatoryError : GuessNumberState()
    data object NumberFormatError : GuessNumberState()
    data object NotEnoughTries : GuessNumberState()
    data object Completed : GuessNumberState()
    data object Success : GuessNumberState()
}
