package com.tipcalculator.ui

sealed interface CalculatorActivityInteraction {
    data class TipCalculationInputsUpdated(val amount: String, val percentage: Float): CalculatorActivityInteraction
}