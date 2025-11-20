package com.example.wirriesassignment2

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.uiautomator.textAsString
import androidx.test.uiautomator.uiAutomator

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.wirriesassignment2", appContext.packageName)
    }

    @Test
    fun myUiTest(){
        uiAutomator {
            // All your UI Automator actions go here
            //Start the app
            startApp("com.example.wirriesassignment2")
            waitForAppToBeVisible("com.example.wirriesassignment2")

            //Click on the button to navigate to 2nd activity
            onElement{textAsString() == "Start Activity Explicitly" }.click()

            waitForStableInActiveWindow()
            //Find text

            val findingText = onElement {
                textAsString()?.contains("Device fragmentation") == true
            }

            // Validate it exists
            assertNotNull("Expected text not found!", findingText)


        }
    }

}