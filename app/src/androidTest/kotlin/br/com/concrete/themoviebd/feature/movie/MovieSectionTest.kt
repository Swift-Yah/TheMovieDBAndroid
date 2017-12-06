package br.com.concrete.themoviebd.feature.movie

import android.support.test.runner.AndroidJUnit4
import br.com.concrete.themoviebd.base.BaseFragmentTest
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MovieSectionTest : BaseFragmentTest() {

    @Test
    fun should_display_all_movie_sections() {
        movieSection {
            nowPlayingSectionIsDisplayed()
            popularSectionIsDisplayed()
            topRatedSectionIsDisplayed()
            upcomingSectionIsDisplayed()
        }
    }

    @Test
    fun click_on_media_item__should_open_movie_detail() {
        movieSection {
            allSectionsDisplayed()
        } clickOnMediaItem {
            movieDetailIsDisplayed()
        }
    }

    @Test
    fun click_on_see_more__should_open_movie_list() {
        movieSection {
            allSectionsDisplayed()
        } clickOnSeeMore {
            movieListIsDisplayed()
        }
    }

}