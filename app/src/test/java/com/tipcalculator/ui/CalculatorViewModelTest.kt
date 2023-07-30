package com.tipcalculator.ui

import com.tipcalculator.ui.CalculatorActivityInteraction.CalculateButtonClicked
import io.mockk.MockKAnnotations
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

internal class CalculatorViewModelTest {

    private lateinit var subject: CalculatorViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        subject = CalculatorViewModel()
    }

    @Test
    fun `calculates tip and updates state flow value when CalculateButtonClicked interaction received`() {
        subject.onInteraction(CalculateButtonClicked(500.0, 20))

        assertEquals(100.0, subject.tipMutableStateFlow.value, 0.00001)
    }

    @Test
    fun `calculates tip with decimal places and updates state flow value when CalculateButtonClicked interaction received`() {
        subject.onInteraction(CalculateButtonClicked(5239.10, 19))

        assertEquals(995.429, subject.tipMutableStateFlow.value, 0.00001)
    }
}