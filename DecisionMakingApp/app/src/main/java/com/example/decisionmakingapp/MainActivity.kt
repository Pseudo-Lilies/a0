package com.example.decisionmakingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.decisionmakingapp.ui.theme.DecisionMakingAppTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DecisionMakingAppTheme {
                AppPreview()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    DecisionMakingApp(modifier = Modifier
        .fillMaxSize()
    )
}

@Composable
fun DecisionMakingApp(modifier: Modifier = Modifier) {
    var result by remember { mutableStateOf(false) }
    var actionMessage by remember { mutableStateOf("[Press a button]") }
    var bgColor by remember { mutableStateOf(Color(0.5f,0.5f,0f)) }
    var clicks by remember { mutableStateOf(0) }

    Box(
        modifier = modifier.background(color = bgColor),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Kady Chan \ncalvin9",
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(horizontal = 4.dp, vertical = 16.dp)
        )

        Text(
            text = "Clicks: $clicks",
            fontSize = 20.sp,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(horizontal = 8.dp, vertical = 20.dp)
        )

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Should we go here?",
                fontSize = 32.sp,
                fontWeight = FontWeight(500)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row() {
                Button(
                    onClick = {
                        result = weightedBool(7)
                        actionMessage = if (result) {"Yes"} else {"No"}
                        bgColor = incrementColor(result, bgColor)
                        clicks++
                    }
                ) {
                    Text(text = "Good")
                }

                Spacer(modifier = Modifier.width(16.dp))

                Button(
                    onClick = {
                        result = weightedBool(5)
                        actionMessage = if (result) {"Yes"} else {"No"}
                        bgColor = incrementColor(result, bgColor)
                        clicks++
                    }
                ) {
                    Text(text = "Okay")
                }

                Spacer(modifier = Modifier.width(16.dp))

                Button(
                    onClick = {
                        result = weightedBool(3)
                        actionMessage = if (result) {"Yes"} else {"No"}
                        bgColor = incrementColor(result, bgColor)
                        clicks++
                    }
                ) {
                    Text(text = "Lame")
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = actionMessage,
                fontSize = 20.sp
            )
        }
    }
}

fun weightedBool(threshold: Int): Boolean {
    return (Random.nextInt(0,9) < threshold)
}

fun incrementColor(upOrDown: Boolean, color: Color): Color {
    return if (upOrDown) {
        Color(color.red - 0.02f, color.green + 0.02f, 0f)
    } else {
        Color(color.red + 0.02f, color.green - 0.02f, 0f)
    }
}