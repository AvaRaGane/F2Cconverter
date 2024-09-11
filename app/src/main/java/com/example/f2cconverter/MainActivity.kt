package com.example.f2cconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.f2cconverter.ui.theme.F2CconverterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            F2CconverterTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    FarenheitCelsius()
                }
            }
        }
    }
}

@Composable
fun FarenheitCelsius() {
    var temperature: String by remember { mutableStateOf("") }
    var farenheitSelected by remember { mutableStateOf(true) }
    var temperatureToConvert: Float = temperature.toFloatOrNull() ?: 0.0f
    val result = when (farenheitSelected) {
        true -> if (temperatureToConvert > 0.0f) (temperatureToConvert -32) /1.8f else 0.0f
        false -> if (temperatureToConvert > 0.0f) (temperatureToConvert * 1.8f) + 32 else 0.0f
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(8.dp)
    ){
        Text(
            text = stringResource(R.string.title),
            color = MaterialTheme.colorScheme.primary,
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = temperature,
            onValueChange = {temperature = it},
            label = { Text(text = stringResource(R.string.temperature))},
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
        )
        Row (verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = farenheitSelected,
                onClick = {farenheitSelected = true}
            )
            Text(stringResource(R.string.farenheit_to_celsius))
        }
        Row (verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = !farenheitSelected,
                onClick = {farenheitSelected = false}
            )
            Text(stringResource(R.string.celsius_to_farenheit))
        }
        Text(text = String.format("%.2f",result))
    }
}