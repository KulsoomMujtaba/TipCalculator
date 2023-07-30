package com.tipcalculator.ui

import androidx.lifecycle.ViewModel
import com.tipcalculator.ui.CalculatorActivityInteraction.CalculateButtonClicked
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CalculatorViewModel @Inject constructor() : ViewModel() {

    private val _tipMutableStateFlow = MutableStateFlow(0.0)
    val tipMutableStateFlow = _tipMutableStateFlow.asStateFlow()

    fun onInteraction(interaction: CalculatorActivityInteraction) {
        when (interaction) {
            is CalculateButtonClicked -> calculateTip(interaction.amount, interaction.percentage)
        }
    }

    private fun calculateTip(amount: Double, tip: Int) {
        _tipMutableStateFlow.value = amount * tip/100
    }
}