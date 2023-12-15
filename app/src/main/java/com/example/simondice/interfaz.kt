package com.example.simondice

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.media3.common.util.UnstableApi


@androidx.annotation.OptIn(UnstableApi::class) @OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IU(VM : MyViewModel) {


    Column {
        Row (Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    text = "RECORD",
                    fontSize = 24.sp
                )


                Text(
                    text = "ROUND",
                    fontSize = 24.sp
                )

        }
        Row (Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    text = "${VM.getHighScore()}",
                    fontSize = 24.sp
                )
                Text(
                    text = "${VM.getRound()}",
                    fontSize = 24.sp
                )
            }
        Column(
            modifier = Modifier.padding(60.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                simonButton(color = Colors.BLUE.color, VM )
                simonButton(color = Colors.GREEN.color , VM )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),

            ) {
                simonButton(color = Colors.RED.color, VM )
                simonButton(color = Colors.YELLOW.color , VM )
            }
        }
        Row (Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Button(onClick = { VM.changePlayStatus()},
                modifier = Modifier.padding(20.dp)
                           .size(100.dp, 50.dp)) {
                Text(text = Data.playStatus.value)
            }

            Button(
                onClick = {
                    if (VM.getGameStatus().equals("Start")){
                        //nothing
                    } else {
                        VM.checkSequence()
                    }
                },
                modifier = Modifier.padding(24.dp)
                    .size(100.dp, 50.dp)

            )
            {
                Image(
                    painter = painterResource(id = R.drawable.baseline_play_arrow_24),

                    contentDescription = "Nuevo numero",


                )
            }
        }

    }



}

@Composable
fun simonButton(color: MutableState<Color>, VM: MyViewModel){
        Button(
            onClick = {
                if ( VM.getClickingFlag() == clickingState.NOTCLICKING && VM.getState() == State.USER){
                    VM.addToUserSequence(Data.colors.indexOf(color))
                    VM.buttonAction(Data.colors.indexOf(color))
                }else{
                    //nothing
                }
            },
            shape = RectangleShape,
            modifier = Modifier
                .height(150.dp)
                .width(150.dp)
                .padding(10.dp, 10.dp)
            ,
            colors = ButtonDefaults.buttonColors(color.value)
        ) {

        }
    }








