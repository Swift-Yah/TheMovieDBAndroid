package br.com.concrete.themoviebd.activity

import android.os.Bundle
import android.support.v7.widget.Toolbar
import br.com.concrete.themoviebd.R
import br.com.concrete.themoviebd.activity.base.BaseActivity
import br.com.concrete.themoviebd.delegate.viewModelProvider
import br.com.concrete.themoviebd.delegate.viewProvider
import br.com.concrete.themoviebd.view.HorizontalSectionView
import br.com.concrete.themoviebd.viewmodel.MainViewModel

class MainActivity : BaseActivity() {

    private val viewModel by viewModelProvider(MainViewModel::class)

    private val toolbar: Toolbar by viewProvider(R.id.toolbar)
    private val nowPlayingSection: HorizontalSectionView by viewProvider(R.id.now_playing_section)
    private val popularSection: HorizontalSectionView by viewProvider(R.id.popular_section)
    private val topRatedSection: HorizontalSectionView by viewProvider(R.id.top_rated_section)
    private val upcomingSection: HorizontalSectionView by viewProvider(R.id.upcoming_section)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        nowPlayingSection.observe(this, viewModel.nowPlayingLiveData)
        popularSection.observe(this, viewModel.popularLiveData)
        topRatedSection.observe(this, viewModel.topRatedLiveData)
        upcomingSection.observe(this, viewModel.upcomingLiveData)
    }

}