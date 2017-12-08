package br.com.concrete.themoviebd.feature.tvshow

import android.support.test.runner.AndroidJUnit4
import br.com.concrete.themoviebd.base.BaseFragmentTest
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TvShowSectionTest : BaseFragmentTest() {

    @Test
    fun should_display_all_tvShow_sections() {
        tvShowSection {
            onTheAirSectionIsDisplayed()
            popularSectionIsDisplayed()
            topRatedSectionIsDisplayed()
            airingTodaySectionIsDisplayed()
        }
    }

    @Test
    fun click_on_media_item__should_open_tvShow_detail() {
        tvShowSection {
            allSectionsDisplayed()
        } clickOnMediaItem {
            tvShowDetailIsDisplayed()
        }
    }

    @Test
    fun click_on_see_more__should_open_tvShow_list() {
        tvShowSection {
            allSectionsDisplayed()
        } clickOnSeeMore {
            tvShowListIsDisplayed()
        }
    }

}