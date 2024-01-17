package com.cbocka.guessnumber.model

class GuessNumberGame private constructor(
    val number : Int, val tries : Int, val player : String, var currentTry : Int = 0) {

    companion object {
        lateinit var instance : GuessNumberGame

        fun initialice(number : Int, tries : Int, player : String, currentTry: Int) {
            instance = GuessNumberGame(number, tries, player, currentTry)
        }
    }
}
