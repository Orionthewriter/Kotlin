package com.example.kotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.kotlin.ui.theme.KotlinTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KotlinTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GameScreen()
                }
            }
        }
    }
}

@Composable
fun GameScreen() {
    var lives by remember { mutableStateOf(13) }
    val wordsToGuess = arrayOf(
        "XYZ",
        "Madolche",
        "Fusion",
        "HERO",
        "DDD",
        "Dragon",
        "SLIFER",
        "CYBERDARK",
        "BLUEEYES",
        "NORDIC"
    )
    var chosenWord by remember { mutableStateOf("") } // Initialize as empty string
    var userGuess by remember { mutableStateOf("") }

    if (chosenWord.isEmpty()) {
        // Choose a random word when chosenWord is empty
        chosenWord = wordsToGuess.random()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("WELCOME TO THE GAME")
        Text("YOU HAVE $lives LIVES TO GET IT CORRECT")

        BasicTextField(
            value = userGuess,
            onValueChange = { userGuess = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        )

        Button(
            onClick = {
                if (userGuess.lowercase() == chosenWord) {
                    // Correct guess
                    print("CONGRATULATIONS")
                } else {
                    // Incorrect guess
                    lives--
                    if (lives > 0) {
                        // Display a hint related to the chosen word
                        val letters=chosenWord.length
                        print("The word has $letters letters")
                        // Handle your logic here (e.g., show a message, update UI, etc.)
                    }
                }
            },
            enabled = lives > 0
        ) {
            Text("Check Guess")
        }
        Button(
            onClick = {
                lives = 13
                chosenWord = wordsToGuess.random()
                userGuess = ""
            }
        ) {
            Text("Reset")
        }
        if (lives == 0) {
            Text("Sorry YOU LOSE!", color = Color.Red)
        }
    }
}
