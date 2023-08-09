package com.tipcalculator.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.tipcalculator.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(
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

@Composable
fun PercentageSlider(
    sliderValue: MutableState<Float>,
    onValueChange: (Float) -> Unit
) {
    Slider(
        value = sliderValue.value,
        onValueChange = onValueChange,
        valueRange = 0f..100f,
        modifier = Modifier.padding(horizontal = 32.dp)
    )

    Text(text = sliderValue.value.toString() + stringResource(id = R.string.percentage))
}