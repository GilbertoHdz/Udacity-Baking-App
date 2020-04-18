package com.manitos.dev.gilinhobakingapp.features.home;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.manitos.dev.gilinhobakingapp.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;

/**
 * Created by gilberto hdz on 12/04/20.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class BakeListActivityTest {

    /**
     * The ActivityTestRule is a rule provided by Android used for functional testing of a single
     * activity. The activity that will be tested will be launched before each test that's annotated
     * with @Test and before methods annotated with @Before. The activity will be terminated after
     * the test and methods annotated with @After are complete. This rule allows you to directly
     * access the activity during the test.
     */
    @Rule
    public ActivityTestRule<BakeListActivity> activityActivityTestRule = new ActivityTestRule(BakeListActivity.class);



    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void onCreate() {

    }

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("com.manitos.dev.gilinhobakingapp",
                appContext.getPackageName());
    }

    @Test
    public void internetConnectionSuccess() {
        onView(withId(R.id.textView))
                .check(matches(not(isDisplayed())));
    }

    @Test
    public void isNotInternetConnection() {
        onView(withId(R.id.textView))
                .check(matches(isDisplayed()));
    }

    @Test
    public void errorConnectionText() {
        onView(withId(R.id.textView))
                .check(matches(withText("No internet connection")));
    }
}