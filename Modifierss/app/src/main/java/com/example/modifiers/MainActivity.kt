package com.example.modifiers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.modifiers.ui.theme.ModifiersTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ModifiersTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MyText(content: String, modifier: Modifier = Modifier) {
    Text(
        text = content,
        modifier = modifier
    )
}
@Composable
fun MyApp(modifier: Modifier = Modifier) {
    val reusableModifier = Modifier
        .padding(8.dp)
        .background(Color.Red)
    Column(
        modifier = modifier
    ) {
        MyText("First text", modifier = reusableModifier)
        MyText(content = "Second text")
        MyText(content = "Third text", Modifier.fillMaxWidth().background(Color.Blue))
        MyText(content = "Fourth text", reusableModifier.fillMaxWidth())
        MyText(content = "Fifth text")
    }
}

@Preview(showBackground = true)
@Composable
private fun MyAppPreview() {
    ModifiersTheme {
        MyApp()
    }
}

