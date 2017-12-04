package br.com.concrete.themoviebd.activity.main

import android.support.test.runner.AndroidJUnit4
import br.com.concrete.themoviebd.activity.base.BaseActivityTest
import br.com.concrete.themoviebd.feature.main.MainActivity
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest : BaseActivityTest<MainActivity>(MainActivity::class) {

    @Test
    fun by_default__movie_section_is_displayed() {
        mainRobot { movieSectionIsDisplayed() }
    }

    @Test
    fun click_on_TVShow_section__should_display_TVShow_section() {
        mainRobot {
            movieSectionIsDisplayed()
        } clickOnTVShowSection {
            tvShowSectionIsDisplayed()
        }
    }

    @Test
    fun click_on_profile__should_display_profile() {
        mainRobot {
            movieSectionIsDisplayed()
        } clickOnProfile {
            profileIsDisplayed()
        }
    }

    @Test
    fun press_back__when_movie_section_is_not_displayed__should_return_to_moview_section() {
        mainRobot {
            withProfileSelected()
        } pressBack {
            movieSectionIsDisplayed()
        }
    }

}