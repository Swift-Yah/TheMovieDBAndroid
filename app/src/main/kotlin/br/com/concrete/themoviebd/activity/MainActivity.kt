package br.com.concrete.themoviebd.activity

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.Button
import android.widget.TextView
import br.com.concrete.sdk.data.ResponseLiveData
import br.com.concrete.sdk.extension.observe
import br.com.concrete.themoviebd.R
import br.com.concrete.themoviebd.activity.base.BaseActivity
import br.com.concrete.themoviebd.delegate.viewModelProvider
import br.com.concrete.themoviebd.delegate.viewProvider
import br.com.concrete.themoviebd.view.HorizontalSectionView
import br.com.concrete.themoviebd.viewmodel.MainViewModel

class MainActivity : BaseActivity() {

    private val viewModel by viewModelProvider(MainViewModel::class)

    private val result: TextView by viewProvider(R.id.result)
    private val toolbar: Toolbar by viewProvider(R.id.toolbar)
    private val config: Button by viewProvider(R.id.config)
    private val latest: Button  by viewProvider(R.id.latest)
    private val nowPlaying: Button by viewProvider(R.id.now_playing)
    private val popular: Button by viewProvider(R.id.popular)
    private val topRated: Button by viewProvider(R.id.top_rated)
    private val upcoming: Button by viewProvider(R.id.upcoming)
    private val popularSection: HorizontalSectionView by viewProvider(R.id.popular_section)

    private val changeContentObserver: (Any) -> Unit = {
        result.text = it.toString()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        addClickListener(
                config to viewModel.configLiveData,
                latest to viewModel.latestLiveData,
                nowPlaying to viewModel.nowPlayingLiveData,
                popular to viewModel.popularLiveData,
                topRated to viewModel.topRatedLiveData,
                upcoming to viewModel.upcomingLiveData
        )

//        popularSection.observe(this, viewModel.popularLiveData)
    }

    private fun observeData(liveData: ResponseLiveData<*>) {
        if (liveData.hasActiveObservers()) liveData.invalidate()
        else liveData.observe(this, changeContentObserver)
    }

    private fun addClickListener(vararg pair: Pair<View, ResponseLiveData<*>>) {
        pair.forEach { it.first.setOnClickListener { _ -> observeData(it.second) } }
    }

}