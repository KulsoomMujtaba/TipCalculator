package com.tipcalculator.ui

sealed interface CalculatorActivityInteraction {
    data class CalculateButtonClicked(val amount: Double, val percentage: Int): CalculatorActivityInteraction
}