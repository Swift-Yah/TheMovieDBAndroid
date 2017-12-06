package br.com.concrete.themoviebd.feature.media

import android.os.Bundle
import br.com.concrete.themoviebd.R
import br.com.concrete.themoviebd.base.BaseActivity

class MediaListActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_media_list)
    }

}