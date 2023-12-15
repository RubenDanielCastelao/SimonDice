package com.example.simondice



import android.util.Log
import android.widget.Toast
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * ViewModel from the app
 */

class MyViewModel : ViewModel() {

    fun getRound(): Int {
        return Data.round.value
    }

    fun getState(): State {
        return Data.state
    }

    fun addToGameSecuence(){
        Data.gameSequence.add(generateRandomNumber(4))
    }

    fun execGameSequence(){
        gameSequence()
    }

    fun showMessage(message: String){
        Toast.makeText(MainActivity.context, message, Toast.LENGTH_SHORT).show()
    }

    fun gameSequence(){
        viewModelScope.launch {
            for (colorIndex in Data.gameSequence) {
                Data.colorFlag = Data.colors[colorIndex].value
                Data.myColors[colorIndex].color.value = darkenColor(Data.colorFlag, 0.5f)
                delay(200L)
                Data.myColors[colorIndex].color.value = Data.colorFlag
                delay(10L * Data.speed.value)
            }
            Data.state = State.USER
            Log.d("GameState",Data.state.toString())
        }
        Log.d("GameState",Data.gameSequence.toString())
    }

    fun buttonAction(colorIndex: Int) {
        viewModelScope.launch {
            Data.colorFlag = Data.colors[colorIndex].value
            Data.myColors[colorIndex].color.value = darkenColor(Data.colorFlag, 0.5f)
            Data.clickingFlag = clickingState.CLICKING
            delay(200L)
            Data.myColors[colorIndex].color.value = Data.colorFlag
            delay(100L)
            Data.clickingFlag = clickingState.NOTCLICKING
        }
    }

    fun getClickingFlag(): clickingState{
        return Data.clickingFlag
    }

    fun changePlayStatus(){
        if (Data.playStatus.value == "Start"){
            Data.playStatus.value = "Reset"
            Data.round.value ++
            roundAction()
        } else{
            Data.playStatus.value = "Start"
            initGame()
        }

    }

    fun initGame(){
        resetRound()
        resetPlayerSecuence()
        resetGameSecuence()
        Data.state = State.START
    }

    fun getGameStatus(): String {
        return Data.playStatus.value
    }

    fun addToUserSequence(color: Int) {
        Log.d("GameState",Data.state.toString())
        Data.userSequence.add(color)
        Log.d("GameState",Data.state.toString())
    }

    fun resetRound(){
        Data.round.value = 0
    }

    fun resetPlayerSecuence(){
        Data.userSequence.clear()
    }

    fun resetGameSecuence(){
        Data.gameSequence.clear()
    }

    fun roundAction(){
        Data.state = State.SEQUENCE
        Log.d("GameState",Data.state.toString())
        addToGameSecuence()
        execGameSequence()
    }

    fun checkSequence(){
        Data.state = State.CHECK
        Log.d("GameState",Data.state.toString())
        if (Data.userSequence == Data.gameSequence){
            showMessage("You won this round, let's go to the next one")
            if (Data.speed.value > 2) {
                Data.speed.value -= 1
            }
            Data.round.value ++
            if (Data.round.value > Data.higherScore.value){
                Data.higherScore.value = Data.round.value
            }
            Data.userSequence.clear()
            addToGameSecuence()
            execGameSequence()
        } else{
            showMessage("You lost. Starting again from round 0")
            Data.state = State.END
            Data.playStatus.value = "Start"
            Data.speed.value = 10
            initGame()
            Log.d("GameState",Data.state.toString())
        }
    }

    fun getHighScore(): Int {
        return Data.higherScore.value
    }

    fun darkenColor(color: Color, factor: Float): Color {
        val r = (color.red * (1 - factor)).coerceIn(0f, 1f)
        val g = (color.green * (1 - factor)).coerceIn(0f, 1f)
        val b = (color.blue * (1 - factor)).coerceIn(0f, 1f)
        return Color(r, g, b, color.alpha)
    }

    fun generateRandomNumber(max: Int): Int {
        return (0..max-1).random()
    }
}
