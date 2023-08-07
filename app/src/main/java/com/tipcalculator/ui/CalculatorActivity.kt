package com.tipcalculator.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tipcalculator.R
import com.tipcalculator.ui.CalculatorActivityInteraction.CalculateButtonClicked
import com.tipcalculator.ui.theme.TipCalculatorTheme
import com.tipcalculator.ui.theme.TipCalculatorTypography
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.roundToInt

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: CalculatorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TipCalculatorTheme {
                Content()
            }
        }

    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun Content() {
        var amount by remember {
            mutableStateOf("")
        }

        var tip by remember {
            mutableStateOf(0.0)
        }

        val sliderValue = remember {
            mutableStateOf(0f)
        }

        tip = viewModel.tipMutableStateFlow.collectAsStateWithLifecycle().value

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = stringResource(id = R.string.calculated_tip),
                    style = TipCalculatorTypography.displayLarge,
                    color = colorResource(id = R.color.teal_700),
                )
                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = tip.toString(),
                    style = TipCalculatorTypography.displayMedium
                )
                Spacer(modifier = Modifier.height(32.dp))

                InputField(
                    amount,
                    onValueChange = { input ->
                        amount = input
                        initiateCalculateTip(amount, sliderValue)
                    },
                    labelText = stringResource(id = R.string.amount),
                    placeholderText = stringResource(id = R.string.placeholder_amount)
                )
                Spacer(modifier = Modifier.height(16.dp))
                PercentageSlider(amount, sliderValue)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }

    @Composable
    private fun PercentageSlider(
        amount: String,
        sliderValue: MutableState<Float>
    ) {
        Slider(
            value = sliderValue.value,
            onValueChange = {
                sliderValue.value = it.roundToInt().toFloat()
                initiateCalculateTip(amount, sliderValue)
            },
            valueRange = 0f..100f,
            modifier = Modifier.padding(horizontal = 32.dp)
        )

        Text(text = sliderValue.value.toString())
    }

    private fun initiateCalculateTip(
        amount: String,
        sliderValue: MutableState<Float>
    ) {
        viewModel.onInteraction(
            CalculateButtonClicked(
                amount,
                sliderValue.value
            )
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun InputField(
        value: String,
        onValueChange: (String) -> Unit,
        labelText: String,
        placeholderText: String, modifier: Modifier = Modifier
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(text = labelText) },
            placeholder = { Text(text = placeholderText) },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),
            modifier = modifier
        )
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        TipCalculatorTheme {
            Content()
        }
    }
}