package br.com.concrete.themoviebd.feature.movie

import android.support.test.rule.ActivityTestRule
import br.com.concrete.themoviebd.R
import br.com.concrete.themoviebd.TestActivity
import br.com.concrete.themoviebd.base.BaseFragmentRobot
import br.com.concrete.themoviebd.extension.mockCreation
import br.com.concrete.themoviebd.factory.model.moviePage
import br.com.concrete.themoviebd.feature.media.MediaListActivity
import br.com.concrete.themoviebd.feature.movie.detail.MovieDetailActivity
import br.com.concrete.themoviebd.sdk.MovieRepository
import br.com.concretesolutions.kappuccino.actions.ClickActions.click
import br.com.concretesolutions.kappuccino.assertions.VisibilityAssertions.displayed
import br.com.concretesolutions.kappuccino.custom.intent.IntentMatcherInteractions.matchIntent

fun MovieSectionTest.movieSection(func: MovieSectionRobot.() -> Unit) =
        MovieSectionRobot(rule).apply({
            MovieRepository.getTopRated().mockCreation(moviePage())
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
        matchIntent {
            className(MovieDetailActivity::class.java.name)
            result { ok() }
        }
        click {
            parent(R.id.top_rated_section) {
                id(R.id.media_item_parent)
            }
        }
        MovieSectionResult().apply(func)
    }

    infix fun clickOnSeeMore(func: MovieSectionResult.() -> Unit) {
        matchIntent {
            className(MediaListActivity::class.java.name)
            result { ok() }
        }
        click {
            parent(R.id.top_rated_section) {
                id(R.id.see_more_item_parent)
            }
        }
        MovieSectionResult().apply(func)
    }

}

class MovieSectionResult {
    fun movieDetailIsDisplayed() {
        matchIntent {
            className(MovieDetailActivity::class.java.name)
        }
    }

    fun movieListIsDisplayed() {
        matchIntent {
            className(MediaListActivity::class.java.name)
        }
    }
}