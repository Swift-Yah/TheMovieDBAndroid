package br.com.concrete.themoviebd.feature.tvshow

import android.support.test.rule.ActivityTestRule
import br.com.concrete.themoviebd.R
import br.com.concrete.themoviebd.TestActivity
import br.com.concrete.themoviebd.base.BaseFragmentRobot
import br.com.concrete.themoviebd.extension.mockCreation
import br.com.concrete.themoviebd.factory.model.tvShowPage
import br.com.concrete.themoviebd.feature.media.MediaListActivity
import br.com.concrete.themoviebd.feature.tvshow.detail.TvShowDetailActivity
import br.com.concrete.themoviebd.sdk.TVShowRepository
import br.com.concretesolutions.kappuccino.actions.ClickActions.click
import br.com.concretesolutions.kappuccino.assertions.VisibilityAssertions.displayed
import br.com.concretesolutions.kappuccino.custom.intent.IntentMatcherInteractions.matchIntent

fun TvShowSectionTest.tvShowSection(func: TvShowSectionRobot.() -> Unit) =
        TvShowSectionRobot(rule).apply({
            TVShowRepository.getTopRated().mockCreation(tvShowPage())
            launchFragment(TVShowSectionFragment())
            func()
        })

class TvShowSectionRobot(rule: ActivityTestRule<TestActivity>) : BaseFragmentRobot(rule) {

    fun onTheAirSectionIsDisplayed() {
        displayed(scroll = true) { id(R.id.on_the_air_section) }
    }

    fun popularSectionIsDisplayed() {
        displayed(scroll = true) { id(R.id.popular_section) }
    }

    fun topRatedSectionIsDisplayed() {
        displayed(scroll = true) { id(R.id.top_rated_section) }
    }

    fun airingTodaySectionIsDisplayed() {
        displayed(scroll = true) { id(R.id.airing_today_section) }
    }

    fun allSectionsDisplayed() {
        onTheAirSectionIsDisplayed()
        popularSectionIsDisplayed()
        topRatedSectionIsDisplayed()
        airingTodaySectionIsDisplayed()
    }

    infix fun clickOnMediaItem(func: TvShowSectionResult.() -> Unit) {
        matchIntent {
            className(TvShowDetailActivity::class.java.name)
            result { ok() }
        }
        click {
            parent(R.id.top_rated_section) {
                id(R.id.media_item_parent)
            }
        }
        TvShowSectionResult().apply(func)
    }

    infix fun clickOnSeeMore(func: TvShowSectionResult.() -> Unit) {
        matchIntent {
            className(MediaListActivity::class.java.name)
            result { ok() }
        }
        click {
            parent(R.id.top_rated_section) {
                id(R.id.see_more_item_parent)
            }
        }
        TvShowSectionResult().apply(func)
    }

}

class TvShowSectionResult {
    fun tvShowDetailIsDisplayed() {
        matchIntent {
            className(TvShowDetailActivity::class.java.name)
        }
    }

    fun tvShowListIsDisplayed() {
        matchIntent {
            className(MediaListActivity::class.java.name)
        }
    }
}