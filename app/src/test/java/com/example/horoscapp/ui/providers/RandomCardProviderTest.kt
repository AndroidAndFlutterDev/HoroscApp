package com.example.horoscapp.ui.providers

// This is the UnitTest of the Random Card Provider: We need to test that the RCP will never launch a null value
import org.junit.Assert.*
import org.junit.Test

class RandomCardProviderTest{

    @Test
    fun `getRandomCard should return a random card`(){
        val randomCard = RandomCardProvider()

        val card = randomCard.getLuck()

        // The assert is like an if: Checks the condition. In this case, if the card is not null (the Test will be Successful)
        assertNotNull(card)
    }
}