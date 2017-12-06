package br.com.concrete.themoviebd.view

import android.support.test.rule.ActivityTestRule
import br.com.concrete.themoviebd.R
import br.com.concrete.themoviebd.TestActivity
import br.com.concrete.themoviebd.base.BaseViewRobot
import br.com.concrete.themoviebd.extension.mockError
import br.com.concrete.themoviebd.extension.mockLoading
import br.com.concrete.themoviebd.extension.mockValue
import br.com.concrete.themoviebd.factory.model.emptyPage
import br.com.concrete.themoviebd.factory.model.mediaItemPage
import br.com.concrete.themoviebd.model.MediaItem
import br.com.concrete.themoviebd.model.mediaItemFrom
import br.com.concrete.themoviebd.sdk.MovieRepository
import br.com.concrete.themoviebd.sdk.data.ResponseLiveData
import br.com.concrete.themoviebd.sdk.extension.mapPage
import br.com.concrete.themoviebd.sdk.model.Page
import br.com.concretesolutions.kappuccino.actions.ClickActions.click
import br.com.concretesolutions.kappuccino.assertions.VisibilityAssertions.displayed
import br.com.concretesolutions.kappuccino.assertions.VisibilityAssertions.notDisplayed

fun MediaSectionTest.mediaSection(func: MediaSectionRobot.() -> Unit) =
        MediaSectionRobot(rule, MovieRepository.getTopRated().mapPage(::mediaItemFrom)).apply({
            val view = MediaSectionView(rule.activity)
            launchView(view)
            view.observe(rule.activity, liveData)
            func()
        })

class MediaSectionRobot(rule: ActivityTestRule<TestActivity>, val liveData: ResponseLiveData<Page<MediaItem>>) : BaseViewRobot(rule) {

    fun emptyStateIsDisplayed() {
        displayed { id(R.id.section_empty) }
        notDisplayed {
            id(R.id.section_loading)
            id(R.id.section_success)
            id(R.id.section_error)
        }
    }

    fun onErrorState() {
        emptyStateIsDisplayed()
        receiveError {
            errorStateIsDisplayed()
        }
    }

    infix fun receiveEmpty(func: MediaSectionResult.() -> Unit) {
        liveData.mockValue(emptyPage())
        MediaSectionResult().apply(func)
    }


    infix fun receiveLoading(func: MediaSectionResult.() -> Unit) {
        liveData.mockLoading()
        MediaSectionResult().apply(func)
    }

    infix fun receiveSuccess(func: MediaSectionResult.() -> Unit) {
        liveData.mockValue(mediaItemPage())
        MediaSectionResult().apply(func)
    }

    infix fun receiveError(func: MediaSectionResult.() -> Unit) {
        liveData.mockError(IllegalStateException())
        MediaSectionResult().apply(func)
    }

    infix fun clickRetry(func: MediaSectionResult.() -> Unit) {
        click { id(R.id.section_error) }
        receiveSuccess(func)
    }

}

class MediaSectionResult {
    fun emptyStateIsDisplayed() {
        displayed { id(R.id.section_empty) }
        notDisplayed {
            id(R.id.section_loading)
            id(R.id.section_success)
            id(R.id.section_error)
        }
    }

    fun loadingStateIsDisplayed() {
        displayed { id(R.id.section_loading) }
        notDisplayed {
            id(R.id.section_empty)
            id(R.id.section_success)
            id(R.id.section_error)
        }
    }

    fun successStateIsDisplayed() {
        displayed { id(R.id.section_success) }
        notDisplayed {
            id(R.id.section_loading)
            id(R.id.section_empty)
            id(R.id.section_error)
        }
    }

    fun errorStateIsDisplayed() {
        displayed { id(R.id.section_error) }
        notDisplayed {
            id(R.id.section_loading)
            id(R.id.section_success)
            id(R.id.section_empty)
        }
    }

    fun stateUpdated() = successStateIsDisplayed()
}