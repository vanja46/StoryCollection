package com.studio.storycollection;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityEndToEndTest {
    @Test
    public void endToEnd1() {
        // End-to-End test for MainActivity
        ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class);
        onView(withText("Cinderella")).perform(click());
        onView(withText("Cinderella\n Once upon a time, there was a kind girl named Cinderella. She lived with her wicked stepmother and two stepsisters who treated her poorly. With the help of her Fairy Godmother, she went to the royal ball and captured the prince's heart. At midnight, she rushed home, leaving behind a glass slipper. The prince found her through the slipper, and they lived happily ever after."))
                .check(matches(isDisplayed()));
    }
}
