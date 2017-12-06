package br.com.concrete.themoviebd.feature.movie.detail

import android.os.Bundle
import br.com.concrete.themoviebd.R
import br.com.concrete.themoviebd.base.BaseActivity

class MovieDetailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
    }

}