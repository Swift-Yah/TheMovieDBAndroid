package br.com.concrete.themoviebd.feature.movie

import android.os.Bundle
import android.view.View
import br.com.concrete.themoviebd.R
import br.com.concrete.themoviebd.base.BaseFragment
import br.com.concrete.themoviebd.delegate.viewModelProvider
import br.com.concrete.themoviebd.delegate.viewProvider
import br.com.concrete.themoviebd.extension.toast
import br.com.concrete.themoviebd.model.MediaItem
import br.com.concrete.themoviebd.view.MediaSectionView

class MovieSectionFragment : BaseFragment(R.layout.fragment_movie_section) {

    private val viewModel by viewModelProvider(MovieSectionViewModel::class)

    private val nowPlayingSection: MediaSectionView by viewProvider(R.id.now_playing_section)
    private val popularSection: MediaSectionView by viewProvider(R.id.popular_section)
    private val topRatedSection: MediaSectionView by viewProvider(R.id.top_rated_section)
    private val upcomingSection: MediaSectionView by viewProvider(R.id.upcoming_section)

    private val onItemClick = { mediaItem: MediaItem -> context?.toast(mediaItem) ?: Unit }
    private val onSeeMoreClick = { view: View -> view.context.toast(view) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Setup Item Click Listeners
        nowPlayingSection.setOnItemClickListener(onItemClick)
        popularSection.setOnItemClickListener(onItemClick)
        topRatedSection.setOnItemClickListener(onItemClick)
        upcomingSection.setOnItemClickListener(onItemClick)

        // Setup See More Click Listeners
        nowPlayingSection.setOnSeeMoreClickListener(onSeeMoreClick)
        popularSection.setOnSeeMoreClickListener(onSeeMoreClick)
        topRatedSection.setOnSeeMoreClickListener(onSeeMoreClick)
        upcomingSection.setOnSeeMoreClickListener(onSeeMoreClick)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Setup Observers
        nowPlayingSection.observe(this, viewModel.nowPlayingLiveData)
        popularSection.observe(this, viewModel.popularLiveData)
        topRatedSection.observe(this, viewModel.topRatedLiveData)
        upcomingSection.observe(this, viewModel.upcomingLiveData)
    }

}