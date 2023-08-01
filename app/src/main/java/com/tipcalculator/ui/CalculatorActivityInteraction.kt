package com.tipcalculator.ui

sealed interface CalculatorActivityInteraction {
    data class CalculateButtonClicked(val amount: String, val percentage: Float): CalculatorActivityInteraction
}