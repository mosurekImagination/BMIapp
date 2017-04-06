package net.mosur.tomasz.lab2.lab2;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class BmiEspressoTest {

    CountBMIForKgM bmiForKgM = new CountBMIForKgM();
    CountBMIForKgM bmiForLbFt = new CountBMIForLbFt();

    @Rule
    public ActivityTestRule<Lab2> mActivityTestRule = new ActivityTestRule<>(Lab2.class);

    @Test
    public void bmiEspressoTest() {
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.input_weight_box),
                        withParent(withId(R.id.activity_lab2))));
        appCompatEditText.perform(scrollTo(), replaceText("70 "), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.input_height_box),
                        withParent(withId(R.id.activity_lab2))));
        appCompatEditText2.perform(scrollTo(), replaceText("1.7 "), closeSoftKeyboard());

        pressBack();

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.bmi_calc_button), withText("Oblicz"),
                        withParent(withId(R.id.activity_lab2))));
        appCompatButton.perform(scrollTo(), click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.bmi_result), withText("24.22"),
                        childAtPosition(
                                allOf(withId(R.id.activity_lab2),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                                0)),
                                8),
                        isDisplayed()));
        textView.check(matches(withText("24.22")));

        ViewInteraction switch_ = onView(
                allOf(withId(R.id.unit_switch), withText("Kg/m"),
                        withParent(withId(R.id.activity_lab2))));
        switch_.perform(scrollTo(), click());

        ViewInteraction switch_2 = onView(
                allOf(withId(R.id.unit_switch), withText("Lb/ft"),
                        withParent(withId(R.id.activity_lab2))));
        switch_2.perform(scrollTo(), click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.bmi_calc_button), withText("Oblicz"),
                        withParent(withId(R.id.activity_lab2))));
        appCompatButton2.perform(scrollTo(), click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.bmi_calc_button), withText("Oblicz"),
                        withParent(withId(R.id.activity_lab2))));
        appCompatButton3.perform(scrollTo(), click());


        ViewInteraction switch_5 = onView(
                allOf(withId(R.id.unit_switch), withText("Kg/m"),
                        withParent(withId(R.id.activity_lab2))));
        switch_5.perform(scrollTo(), click());

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.bmi_calc_button), withText("Oblicz"),
                        withParent(withId(R.id.activity_lab2))));
        appCompatButton4.perform(scrollTo(), click());

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.bmi_result), withText("115.51"),
                        childAtPosition(
                                allOf(withId(R.id.activity_lab2),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                                0)),
                                8),
                        isDisplayed()));
        textView2.check(matches(withText("115.51")));

        ViewInteraction switch_6 = onView(
                allOf(withId(R.id.unit_switch), withText("Lb/ft"),
                        withParent(withId(R.id.activity_lab2))));
        switch_6.perform(scrollTo(), click());

        ViewInteraction appCompatButton5 = onView(
                allOf(withId(R.id.bmi_calc_button), withText("Oblicz"),
                        withParent(withId(R.id.activity_lab2))));
        appCompatButton5.perform(scrollTo(), click());

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.bmi_result), withText("24.22"),
                        childAtPosition(
                                allOf(withId(R.id.activity_lab2),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                                0)),
                                8),
                        isDisplayed()));
        textView3.check(matches(withText("24.22")));

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
