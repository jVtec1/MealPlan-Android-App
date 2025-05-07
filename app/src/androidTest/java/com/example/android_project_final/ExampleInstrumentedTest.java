package com.example.android_project_final;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import android.content.Context;
import android.content.Intent;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.android_project_final", appContext.getPackageName());
    }

    @Test
    public void addsCorrectUserId(){
        Context context = ApplicationProvider.getApplicationContext();
        Intent intent = LandingActivity.landingActivityIntentFactory(context, 123);
        int userId = intent.getIntExtra("com.example.android_project_final.LANDING_ACTIVITY_USER_ID", -1);
        assertEquals(123, userId);
    }
    @Test
    public void defaultUserIDMinusOne(){
        Context context = ApplicationProvider.getApplicationContext();
        Intent intent = new Intent(context, LandingActivity.class);
        //No extras in intent
        int userId = intent.getIntExtra("com.example.android_project_final.LANDING_ACTIVITY_USER_ID", -1);
        // should be -1 because no extra was added
        assertEquals(-1, userId);
    }
}