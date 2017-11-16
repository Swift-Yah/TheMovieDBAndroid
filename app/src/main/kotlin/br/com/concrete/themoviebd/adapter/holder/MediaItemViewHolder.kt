package br.com.concrete.themoviebd.adapter.holder

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import br.com.concrete.sdk.model.type.MEDIUM
import br.com.concrete.sdk.model.type.POSTER
import br.com.concrete.themoviebd.R
import br.com.concrete.themoviebd.base.BaseViewHolder
import br.com.concrete.themoviebd.delegate.viewProvider
import br.com.concrete.themoviebd.extension.loadUrl
import br.com.concrete.themoviebd.model.MediaItem

class MediaItemViewHolder(inflater: LayoutInflater, parent: ViewGroup) : BaseViewHolder<MediaItem>(inflater, parent, R.layout.item_movie) {

    private val poster: ImageView by viewProvider(R.id.item_poster)
    private val title: TextView by viewProvider(R.id.item_title)

    override fun bind(model: MediaItem) {
        poster.loadUrl(MEDIUM, POSTER, model.imagePath)
        title.text = model.title
    }

}