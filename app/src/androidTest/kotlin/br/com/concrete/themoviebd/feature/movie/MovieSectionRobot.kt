package br.com.concrete.themoviebd.feature.movie

import android.support.test.rule.ActivityTestRule
import br.com.concrete.themoviebd.R
import br.com.concrete.themoviebd.TestActivity
import br.com.concrete.themoviebd.base.BaseFragmentRobot
import br.com.concretesolutions.kappuccino.assertions.VisibilityAssertions.displayed

fun MovieSectionTest.movieSection(func: MovieSectionRobot.() -> Unit) =
        MovieSectionRobot(rule).apply({
            launchFragment(MovieSectionFragment())
            func()
        })

class MovieSectionRobot(rule: ActivityTestRule<TestActivity>) : BaseFragmentRobot(rule) {

    fun nowPlayingSectionIsDisplayed() {
        displayed(scroll = true) { id(R.id.now_playing_section) }
    }

    fun popularSectionIsDisplayed() {
        displayed(scroll = true) { id(R.id.popular_section) }
    }

    fun topRatedSectionIsDisplayed() {
        displayed(scroll = true) { id(R.id.top_rated_section) }
    }

    fun upcomingSectionIsDisplayed() {
        displayed(scroll = true) { id(R.id.upcoming_section) }
    }

    fun allSectionsDisplayed() {
        nowPlayingSectionIsDisplayed()
        popularSectionIsDisplayed()
        topRatedSectionIsDisplayed()
        upcomingSectionIsDisplayed()
    }

    infix fun clickOnMediaItem(func: MovieSectionResult.() -> Unit) {
        MovieSectionResult().apply(func)
    }

    infix fun clickOnSeeMore(func: MovieSectionResult.() -> Unit) {
        MovieSectionResult().apply(func)
    }

}

class MovieSectionResult {
    fun movieDetailIsDisplayed() {}
    fun movieListIsDisplayed() {}
}