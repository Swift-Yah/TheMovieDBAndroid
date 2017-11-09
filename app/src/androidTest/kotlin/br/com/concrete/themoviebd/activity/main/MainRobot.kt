package br.com.concrete.themoviebd.activity.main

import android.support.annotation.IdRes
import android.support.test.rule.ActivityTestRule
import br.com.concrete.sdk.model.DataResult
import br.com.concrete.themoviebd.R
import br.com.concrete.themoviebd.activity.MainActivity
import br.com.concrete.themoviebd.activity.base.BaseRobot
import br.com.concrete.themoviebd.extension.mockResponse
import br.com.concrete.themoviebd.viewmodel.MainViewModel
import br.com.concretesolutions.kappuccino.actions.ClickActions.click
import br.com.concretesolutions.kappuccino.assertions.VisibilityAssertions.displayed

fun MainActivityTest.mainRobot(func: MainRobot.() -> Unit) =
        MainRobot(rule, mockViewModelForClass(MainViewModel::class)).apply(func)

class MainRobot(rule: ActivityTestRule<MainActivity>, private val viewModel: MainViewModel) : BaseRobot<MainActivity>(rule) {

    private var result: DataResult<*>? = null

    fun withPopularMovies() {
        result = viewModel.popularLiveData.mockResponse(DataResult(null, null, 50))
        launchActivity()
    }

    fun withTopRatedMovies() {
        result = viewModel.topRatedLiveData.mockResponse(DataResult(null, null, 50))
        launchActivity()
    }

    fun withUpComingMovies() {
        result = viewModel.upcomingLiveData.mockResponse(DataResult(null, null, 50))
        launchActivity()
    }

    fun withConfig() {
        result = viewModel.configLiveData.mockResponse(DataResult(null, null, 50))
        launchActivity()
    }

    fun withLatestMovie() {
        result = viewModel.latestLiveData.mockResponse(DataResult(null, null, 50))
        launchActivity()
    }

    fun withNowPlayingMovies() {
        result = viewModel.nowPlayingLiveData.mockResponse(DataResult(null, null, 50))
        launchActivity()
    }

    infix fun clickOnPopular(func: MainResult.() -> Unit) = clickOnButton(R.id.popular, func)

    infix fun clickOnTopRated(func: MainResult.() -> Unit) = clickOnButton(R.id.top_rated, func)

    infix fun clickOnUpComing(func: MainResult.() -> Unit) = clickOnButton(R.id.upcoming, func)

    infix fun clickOnConfig(func: MainResult.() -> Unit) = clickOnButton(R.id.config, func)

    infix fun clickOnLatest(func: MainResult.() -> Unit) = clickOnButton(R.id.latest, func)

    infix fun clickOnNowPlaying(func: MainResult.() -> Unit) = clickOnButton(R.id.now_playing, func)

    private fun clickOnButton(@IdRes id: Int, func: MainResult.() -> Unit) {
        click { id(id) }
        MainResult(result!!).apply(func)
    }

}

class MainResult(private val result: Any) {

    fun popularMoviesDisplayed() = checkResultText()

    fun topRatedMoviesDisplayed() = checkResultText()

    fun upComingMoviesDisplayed() = checkResultText()

    fun configDisplayed() = checkResultText()

    fun latestMovieDisplayed() = checkResultText()

    fun nowPlayingMoviesDisplayed() = checkResultText()

    private fun checkResultText() {
        displayed {
            allOf {
                id(R.id.result)
                text(result.toString())
            }
        }
    }

}