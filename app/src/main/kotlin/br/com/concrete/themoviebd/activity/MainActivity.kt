package br.com.concrete.themoviebd.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.concrete.themoviebd.R
import br.com.concrete.themoviebd.delegate.viewModelProvider
import br.com.concrete.themoviebd.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModelProvider(MainViewModel::class)

    private val changeContentObserver: (Any) -> Unit = {
        result.text = it.toString()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        config.setOnClickListener {
            viewModel.configLiveData.observeSingle(this, changeContentObserver)
            viewModel.configLiveData.invalidate()
        }

        latest.setOnClickListener {
            viewModel.latestLiveData.observeSingle(this, changeContentObserver)
            viewModel.latestLiveData.invalidate()
        }

        now_playing.setOnClickListener {
            viewModel.nowPlayingLiveData.observeSingle(this, changeContentObserver)
            viewModel.nowPlayingLiveData.invalidate()
        }

        popular.setOnClickListener {
            viewModel.popularLiveData.observeSingle(this, changeContentObserver)
            viewModel.popularLiveData.invalidate()
        }

        top_rated.setOnClickListener {
            viewModel.topRatedLiveData.observeSingle(this, changeContentObserver)
            viewModel.topRatedLiveData.invalidate()
        }

        upcoming.setOnClickListener {
            viewModel.upcomingLiveData.observeSingle(this, changeContentObserver)
        }
    }

}