package br.com.concrete.themoviebd.activity

import android.os.Bundle
import android.view.View
import br.com.concrete.sdk.data.ResponseLiveData
import br.com.concrete.sdk.extension.observe
import br.com.concrete.themoviebd.R
import br.com.concrete.themoviebd.activity.base.BaseActivity
import br.com.concrete.themoviebd.delegate.viewModelProvider
import br.com.concrete.themoviebd.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private val viewModel by viewModelProvider(MainViewModel::class)

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
                now_playing to viewModel.nowPlayingLiveData,
                popular to viewModel.popularLiveData,
                top_rated to viewModel.topRatedLiveData,
                upcoming to viewModel.upcomingLiveData
        )
    }

    private fun observeData(liveData: ResponseLiveData<*>) {
        if (liveData.hasActiveObservers()) liveData.invalidate()
        else liveData.observe(this, changeContentObserver)
    }

    private fun addClickListener(vararg pair: Pair<View, ResponseLiveData<*>>) {
        pair.forEach { it.first.setOnClickListener { _ -> observeData(it.second) } }
    }

}