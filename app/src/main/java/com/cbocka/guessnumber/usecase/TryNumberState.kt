package com.cbocka.guessnumber.usecase

sealed class TryNumberState() {
    data object EmptyNumber : TryNumberState()
    data object WrongNumberFormat : TryNumberState()
    data object IsLower : TryNumberState()
    data object IsBigger : TryNumberState()
    data object NoMoreTries : TryNumberState()
    data object Win : TryNumberState()
}
