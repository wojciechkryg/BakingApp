package com.wojdor.bakingapp.testui;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

abstract class BaseTestView {

    void viewWithTextIsDisplayed(String text) {
        onView(withText(text)).check(matches(isDisplayed()));
    }

    void viewHasText(int viewResId, String text) {
        onView(withId(viewResId)).check(matches(withText(text)));
    }
}
