package com.studio.storycollection;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.MediumTest;

import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@MediumTest
public class MainActivityMediumTest {
    @Test
    public void medium1() {
        ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class);
        Espresso.onView(ViewMatchers.withId(R.id.storyListView)).check(matches(withId(R.id.storyListView)));
    }

    @Test
    public void medium2() {
        ActivityScenario<ViewStoryActivity> scenario = ActivityScenario.launch(ViewStoryActivity.class);
        Espresso.onView(ViewMatchers.withId(R.id.storyTextView)).check(matches(withId(R.id.storyTextView)));
    }
}
