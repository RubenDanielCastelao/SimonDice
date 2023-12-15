package com.example.simondice

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color

/**
 * Data class for the simon says game
 * @property round Number of the round
 * @property sequence Sequence of the game colors
 * @property userSequence Sequence of the user colors
 * @property score Score of the user
 */

object Data {
    var round = mutableStateOf(0)
    var playStatus = mutableStateOf("Start")
    var userSequence = mutableListOf<Int>()
    var gameSequence = mutableListOf<Int>()
    var higherScore = mutableStateOf(0)
    var state = State.START
    var colors = listOf(
        Colors.BLUE.color,
        Colors.GREEN.color,
        Colors.RED.color,
        Colors.YELLOW.color
    )
    var myColors = Colors.values()
    var colorFlag: Color = Color.White
    var clickingFlag = clickingState.NOTCLICKING
    var speed = mutableStateOf<Int>(10)

}

enum class State {
    START,
    SEQUENCE,
    WAITING,
    USER,
    END,
    CHECK
}

enum class clickingState {
    CLICKING,
    NOTCLICKING
}

enum class Colors(val color: MutableState<Color>){
    BLUE(mutableStateOf(Color.Blue)),
    GREEN(mutableStateOf(Color.Green)),
    RED(mutableStateOf(Color.Red)),
    YELLOW(mutableStateOf(Color.Yellow))
}