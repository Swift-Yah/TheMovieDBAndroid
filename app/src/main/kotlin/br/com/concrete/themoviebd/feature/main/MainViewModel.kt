package br.com.concrete.themoviebd.feature.main

import android.support.annotation.IdRes
import br.com.concrete.themoviebd.R
import br.com.concrete.themoviebd.base.BaseViewModel
import br.com.concrete.themoviebd.feature.movie.MovieSectionFragment

class MainViewModel : BaseViewModel() {

    var selectedItemId: Int = 0

    fun shouldFinish() = selectedItemId == INITIAL_SELECTED_ID

    fun fragmentTagById(@IdRes id: Int): String {
        return MENU_ID_TAG_MAP[id] ?: throw IllegalArgumentException("Id: $id does not have a respective Tag")
    }

    fun createFragmentByTag(tag: String) = when (tag) {
        MENU_MOVIE_SECTION_TAG -> MovieSectionFragment()
        MENU_TV_SHOW_SECTION_TAG -> MovieSectionFragment()
        MENU_PROFILE_TAG -> MovieSectionFragment()
        else -> throw IllegalArgumentException("Impossible to create a Fragment with tag: $tag")
    }

    private companion object {

        private const val MENU_MOVIE_SECTION_ID = R.id.main_navigation_movie
        private const val MENU_TV_SHOW_SECTION_ID = R.id.main_navigation_tv_show
        private const val MENU_PROFILE_ID = R.id.main_navigation_profile

        private const val MENU_MOVIE_SECTION_TAG = "MENU_MOVIE_SECTION_TAG"
        private const val MENU_TV_SHOW_SECTION_TAG = "MENU_TV_SHOW_SECTION_TAG"
        private const val MENU_PROFILE_TAG = "MENU_PROFILE_TAG"

        private const val INITIAL_SELECTED_ID = MENU_MOVIE_SECTION_ID

        private val MENU_ID_TAG_MAP = hashMapOf(
                MENU_MOVIE_SECTION_ID to MENU_MOVIE_SECTION_TAG,
                MENU_TV_SHOW_SECTION_ID to MENU_TV_SHOW_SECTION_TAG,
                MENU_PROFILE_ID to MENU_PROFILE_TAG
        )
    }

}