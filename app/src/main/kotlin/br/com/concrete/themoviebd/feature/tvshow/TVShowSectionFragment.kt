package br.com.concrete.themoviebd.feature.tvshow

import android.content.Intent
import android.os.Bundle
import android.view.View
import br.com.concrete.themoviebd.R
import br.com.concrete.themoviebd.base.BaseFragment
import br.com.concrete.themoviebd.delegate.viewModelProvider
import br.com.concrete.themoviebd.delegate.viewProvider
import br.com.concrete.themoviebd.feature.media.MediaListActivity
import br.com.concrete.themoviebd.feature.tvshow.detail.TvShowDetailActivity
import br.com.concrete.themoviebd.model.MediaItem
import br.com.concrete.themoviebd.view.MediaSectionView

class TVShowSectionFragment : BaseFragment(R.layout.fragment_tv_show_section) {

    private val viewModel by viewModelProvider(TvShowSectionViewModel::class)

    private val onTheAirSection: MediaSectionView by viewProvider(R.id.on_the_air_section)
    private val popularSection: MediaSectionView by viewProvider(R.id.popular_section)
    private val topRatedSection: MediaSectionView by viewProvider(R.id.top_rated_section)
    private val airingTodaySection: MediaSectionView by viewProvider(R.id.airing_today_section)

    private val onItemClick = { _: MediaItem ->
        context?.let {
            it.startActivity(Intent(it, TvShowDetailActivity::class.java))
        } ?: Unit
    }
    private val onSeeMoreClick = { view: View ->
        view.context.let {
            it.startActivity(Intent(it, MediaListActivity::class.java))
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Setup Item Click Listeners
        onTheAirSection.setOnItemClickListener(onItemClick)
        popularSection.setOnItemClickListener(onItemClick)
        topRatedSection.setOnItemClickListener(onItemClick)
        airingTodaySection.setOnItemClickListener(onItemClick)

        // Setup See More Click Listeners
        onTheAirSection.setOnSeeMoreClickListener(onSeeMoreClick)
        popularSection.setOnSeeMoreClickListener(onSeeMoreClick)
        topRatedSection.setOnSeeMoreClickListener(onSeeMoreClick)
        airingTodaySection.setOnSeeMoreClickListener(onSeeMoreClick)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Setup Observers
        onTheAirSection.observe(this, viewModel.onTheAirLiveData)
        popularSection.observe(this, viewModel.popularLiveData)
        topRatedSection.observe(this, viewModel.topRatedLiveData)
        airingTodaySection.observe(this, viewModel.airingTodayLiveData)
    }

}