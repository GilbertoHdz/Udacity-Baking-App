package com.manitos.dev.gilinhobakingapp.features.home;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by gilberto hdz on 12/04/20.
 */
@RunWith(AndroidJUnit4.class)
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
}