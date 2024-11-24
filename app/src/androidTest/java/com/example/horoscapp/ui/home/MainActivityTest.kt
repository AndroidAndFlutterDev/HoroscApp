package com.example.horoscapp.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.horoscapp.R
import com.example.horoscapp.ui.detail.HoroscopeDetailActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class MainActivityTest{

    // This rule is used to inject dependencies into the activity
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val mainActivityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp(){

        // This injects the Hilt Rule into the Activity
        hiltRule.inject()

        Intents.init()
    }

    @After
    fun tearDown(){
        Intents.release()
    }
    
    @Test
    fun when_main_activity_is_created_then_open_LuckFragment(){

        // In the MainActivity, search for the LuckFragment. That is, the button that will click when the search has finished
        onView(withId(R.id.luckFragment)).perform(click())
    }

    @Test
    fun when_horoscope_is_selected_then_open_detail(){

        /**
         * This is almost the same as the other search and click, only that we can't just CLICK, as it is,
         * because it is a RecyclerView: That means that we have to specify the position of the item that we want to click
         */

        val recyclerViewItem0Clicked = RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())

        onView(withId(R.id.rvHoroscope)).perform(recyclerViewItem0Clicked)
        intended(hasComponent(HoroscopeDetailActivity::class.java.name))
    }
}