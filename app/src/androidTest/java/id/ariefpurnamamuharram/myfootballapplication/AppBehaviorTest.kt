package id.ariefpurnamamuharram.myfootballapplication

import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import id.ariefpurnamamuharram.myfootballapplication.R.id.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AppBehaviorTest {
    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testLastMatchFragmentBehaviour() {

        // LastMatchFragment behavior test
        Espresso.onView(withId(bottom_nav)).check(matches(isDisplayed()))
        Espresso.onView(withId(nav_matches)).perform(click())
        Thread.sleep(2000)
        Espresso.onView(withId(last_match_list)).check(matches(isDisplayed()))
        Espresso.onView(withId(last_match_list)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        Thread.sleep(1000)
        Espresso.onView(withId(last_match_list)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10, click()))
        Thread.sleep(2000)
        Espresso.onView(withId(add_to_favorite)).check(matches(isDisplayed()))
        Espresso.onView(withId(add_to_favorite)).perform(click())
        Thread.sleep(1000)
        Espresso.pressBack()

    }

}