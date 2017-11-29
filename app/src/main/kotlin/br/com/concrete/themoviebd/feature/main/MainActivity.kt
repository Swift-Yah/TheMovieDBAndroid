package br.com.concrete.themoviebd.feature.main

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import br.com.concrete.themoviebd.R
import br.com.concrete.themoviebd.base.BaseActivity
import br.com.concrete.themoviebd.delegate.nullableViewProvider
import br.com.concrete.themoviebd.delegate.viewModelProvider
import br.com.concrete.themoviebd.delegate.viewProvider
import br.com.concrete.themoviebd.extension.toast
import br.com.concrete.themoviebd.model.MediaItem
import br.com.concrete.themoviebd.view.HorizontalSectionView

class MainActivity : BaseActivity() {

    private val viewModel by viewModelProvider(MainViewModel::class)

    private val toolbar: Toolbar by viewProvider(R.id.toolbar)
    private val nowPlayingSection: HorizontalSectionView by viewProvider(R.id.now_playing_section)
    private val popularSection: HorizontalSectionView by viewProvider(R.id.popular_section)
    private val topRatedSection: HorizontalSectionView by viewProvider(R.id.top_rated_section)
    private val upcomingSection: HorizontalSectionView by viewProvider(R.id.upcoming_section)

    private val onItemClick = { mediaItem: MediaItem -> toast(mediaItem) }
    private val onSeeMoreClick = { view: View -> toast(view) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

//        Setup Item Click Listeners
        nowPlayingSection.setOnItemClickListener(onItemClick)
        popularSection.setOnItemClickListener(onItemClick)
        topRatedSection.setOnItemClickListener(onItemClick)
        upcomingSection.setOnItemClickListener(onItemClick)

//        Setup See More Click Listeners
        nowPlayingSection.setOnSeeMoreClickListener(onSeeMoreClick)
        popularSection.setOnSeeMoreClickListener(onSeeMoreClick)
        topRatedSection.setOnSeeMoreClickListener(onSeeMoreClick)
        upcomingSection.setOnSeeMoreClickListener(onSeeMoreClick)

//        Setup Observers
        nowPlayingSection.observe(this, viewModel.nowPlayingLiveData)
        popularSection.observe(this, viewModel.popularLiveData)
        topRatedSection.observe(this, viewModel.topRatedLiveData)
        upcomingSection.observe(this, viewModel.upcomingLiveData)
    }

}