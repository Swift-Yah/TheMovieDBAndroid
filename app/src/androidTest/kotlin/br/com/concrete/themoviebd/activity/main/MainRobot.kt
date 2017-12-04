package br.com.concrete.themoviebd.activity.main

import android.support.test.espresso.Espresso
import android.support.test.rule.ActivityTestRule
import br.com.concrete.themoviebd.R
import br.com.concrete.themoviebd.activity.base.BaseRobot
import br.com.concrete.themoviebd.factory.viewmodel.mockMainViewModel
import br.com.concrete.themoviebd.feature.main.MainActivity
import br.com.concrete.themoviebd.feature.main.MainViewModel
import br.com.concretesolutions.kappuccino.actions.ClickActions.click
import br.com.concretesolutions.kappuccino.assertions.ExistanceAssertions.notExist
import br.com.concretesolutions.kappuccino.assertions.VisibilityAssertions.displayed

fun MainActivityTest.mainRobot(func: MainRobot.() -> Unit) =
        MainRobot(rule, mockMainViewModel()).apply {
            launchActivity()
            func()
        }

class MainRobot(rule: ActivityTestRule<MainActivity>, viewModel: MainViewModel) : BaseRobot<MainActivity, MainViewModel>(rule, viewModel) {

    fun movieSectionIsDisplayed() {
        displayed { id(R.id.movie_section_parent) }
        notExist {
            id(R.id.tv_show_section_parent)
            id(R.id.profile_parent)
        }
    }

    fun withProfileSelected() {
        movieSectionIsDisplayed()
        clickOnTVShowSection {
            tvShowSectionIsDisplayed()
        }
    }

    infix fun clickOnTVShowSection(func: MainResult.() -> Unit) {
        click { id(R.id.main_navigation_tv_show) }
        MainResult().apply(func)
    }

    infix fun clickOnProfile(func: MainResult.() -> Unit) {
        click { id(R.id.main_navigation_profile) }
        MainResult().apply(func)
    }

    infix fun pressBack(func: MainResult.() -> Unit) {
        Espresso.pressBack()
        MainResult().apply(func)
    }

}

class MainResult {

    fun movieSectionIsDisplayed() {
        displayed { id(R.id.movie_section_parent) }
        notExist {
            id(R.id.tv_show_section_parent)
            id(R.id.profile_parent)
        }
    }

    fun tvShowSectionIsDisplayed() {
        displayed { id(R.id.tv_show_section_parent) }
        notExist {
            id(R.id.movie_section_parent)
            id(R.id.profile_parent)
        }
    }

    fun profileIsDisplayed() {
        displayed { id(R.id.profile_parent) }
        notExist {
            id(R.id.tv_show_section_parent)
            id(R.id.movie_section_parent)
        }
    }

}