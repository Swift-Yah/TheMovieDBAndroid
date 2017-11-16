package br.com.concrete.themoviebd.activity.main

import android.support.test.runner.AndroidJUnit4
import br.com.concrete.themoviebd.feature.main.MainActivity
import br.com.concrete.themoviebd.activity.base.BaseActivityTest
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest : BaseActivityTest<MainActivity>(MainActivity::class) {

    @Test
    fun click_on_popular_should_display_popular_movies() {
        mainRobot {
            withPopularMovies()
        } clickOnPopular {
            popularMoviesDisplayed()
        }
    }

    @Test
    fun click_on_top_rated_should_display_top_rated_movies() {
        mainRobot {
            withTopRatedMovies()
        } clickOnTopRated {
            topRatedMoviesDisplayed()
        }
    }

    @Test
    fun click_on_upComing_should_display_up_coming_movies() {
        mainRobot {
            withUpComingMovies()
        } clickOnUpComing {
            upComingMoviesDisplayed()
        }
    }

    @Test
    fun click_on_config_should_display_config() {
        mainRobot {
            withConfig()
        } clickOnConfig {
            configDisplayed()
        }
    }

    @Test
    fun click_on_latest_should_display_the_latest_movie() {
        mainRobot {
            withLatestMovie()
        } clickOnLatest {
            latestMovieDisplayed()
        }
    }

    @Test
    fun click_on_now_playing_should_display_now_playing_movies() {
        mainRobot {
            withNowPlayingMovies()
        } clickOnNowPlaying {
            nowPlayingMoviesDisplayed()
        }
    }

}