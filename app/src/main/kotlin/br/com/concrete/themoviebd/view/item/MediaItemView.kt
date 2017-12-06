package br.com.concrete.themoviebd.view.item

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import br.com.concrete.themoviebd.sdk.model.type.MEDIUM
import br.com.concrete.themoviebd.sdk.model.type.POSTER
import br.com.concrete.themoviebd.R
import br.com.concrete.themoviebd.base.ViewBinder
import br.com.concrete.themoviebd.delegate.viewProvider
import br.com.concrete.themoviebd.extension.loadUrl
import br.com.concrete.themoviebd.extension.obtain
import br.com.concrete.themoviebd.extension.setCustomTextAppearance
import br.com.concrete.themoviebd.model.MediaItem

class MediaItemView : LinearLayout, ViewBinder<MediaItem> {

    private val title: TextView by viewProvider(R.id.item_title)
    private val poster: ImageView by viewProvider(R.id.item_poster)

    init {
        inflate(context, R.layout.item_media, this)
        orientation = VERTICAL
    }

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, R.attr.mediaItemViewStyle)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        setup(context.obtainStyledAttributes(attrs, R.styleable.MediaItemView, defStyleAttr, defStyleRes))
    }

    private fun setup(typedArray: TypedArray) = typedArray.obtain {
        title.setCustomTextAppearance(getResourceId(R.styleable.MediaItemView_titleTextAppearance, 0))
    }

    override fun bind(model: MediaItem) {
        title.text = model.title
        poster.loadUrl(MEDIUM, POSTER, model.imagePath)
    }

}