package br.com.concrete.themoviebd.view

import android.support.test.runner.AndroidJUnit4
import br.com.concrete.themoviebd.base.BaseViewTest
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MediaSectionTest : BaseViewTest() {

    @Test
    fun by_default__empty_state_is_displayed() {
        mediaSection {
            emptyStateIsDisplayed()
        }
    }

    @Test
    fun when_receive_loading__should_display_loading_state() {
        mediaSection {
            emptyStateIsDisplayed()
        } receiveLoading {
            loadingStateIsDisplayed()
        }
    }

    @Test
    fun when_receive_empty_page__should_display_empty_state() {
        mediaSection {
            emptyStateIsDisplayed()
        } receiveEmpty {
            emptyStateIsDisplayed()
        }
    }

    @Test
    fun when_receive_error__should_display_error_state() {
        mediaSection {
            emptyStateIsDisplayed()
        } receiveError {
            errorStateIsDisplayed()
        }
    }

    @Test
    fun when_receive_success_page__should_display_success_state() {
        mediaSection {
            emptyStateIsDisplayed()
        } receiveSuccess {
            successStateIsDisplayed()
        }
    }

    @Test
    fun on_error_state__click_on_retry__should_invalidate_and_update_state() {
        mediaSection {
            onErrorState()
        } clickRetry {
            stateUpdated()
        }
    }


}