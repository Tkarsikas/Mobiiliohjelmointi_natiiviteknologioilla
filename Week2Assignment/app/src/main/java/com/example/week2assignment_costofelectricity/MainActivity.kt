package com.example.week2assignment_costofelectricity

import android.R.attr.text
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.week2assignment_costofelectricity.ui.theme.Week2Assignment_CostOfElectricityTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Week2Assignment_CostOfElectricityTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp(modifier: Modifier = Modifier) {
    var consumption by remember { mutableStateOf("") }
    var price by remember { mutableFloatStateOf(0f) }
    val VAT = 10.0
    var includeVat by remember { mutableStateOf(false) }

    val consumptionKwh = consumption.toDoubleOrNull() ?: 0.0
    val priceKwh = price

    val baseCost = consumptionKwh * priceKwh
    val totalCost = if (includeVat) {
        baseCost * (1+VAT / 100)
    }else {
        baseCost
    }



    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = "Cost of electricity"
        )
        OutlinedTextField(
            value = consumption,
            onValueChange = { consumption = it },
            label = {Text("Consumption")},
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = "Price/kWh: %.2f".format(price)
        )
        Slider(
            value = price,
            onValueChange = { price = it },
            valueRange = 0f..1f,
            steps = 99,
            modifier = Modifier.fillMaxWidth(),
            thumb = {
                Box(
                    modifier = Modifier
                        .size(20.dp)
                        .background(MaterialTheme.colorScheme.primary, CircleShape)
                )
            },
            track = { sliderState ->
                SliderDefaults.Track(
                    sliderState = sliderState,
                    modifier = Modifier.height(4.dp),
                    drawTick = { _, _ -> },
                    drawStopIndicator = null,
                    thumbTrackGapSize = 0.dp
                )
            }
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = includeVat,
                onCheckedChange = { includeVat = it }
            )
            Text(
                text = "VAT $VAT%"
            )
        }
        Surface(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .size((80.dp)),
            shape = RectangleShape,
            color = Color(0xFF1565C0),
            contentColor = Color.White

        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "%.0f €".format(totalCost))
                }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun MyAppPreview() {
    Week2Assignment_CostOfElectricityTheme {
        MyApp()
    }
}