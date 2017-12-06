package br.com.concrete.themoviebd.view.item

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView
import br.com.concrete.themoviebd.R
import br.com.concrete.themoviebd.base.ViewBinder
import br.com.concrete.themoviebd.delegate.viewProvider
import br.com.concrete.themoviebd.extension.obtain
import br.com.concrete.themoviebd.extension.setCustomTextAppearance

class SeeMoreItemView : LinearLayout, ViewBinder<Int> {

    private val title: TextView by viewProvider(R.id.item_title)

    init {
        inflate(context, R.layout.item_media, this)
        orientation = VERTICAL
        id = R.id.see_more_item_parent
    }

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, R.attr.seeMoreItemViewStyle)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        setup(context.obtainStyledAttributes(attrs, R.styleable.SeeMoreItemView, defStyleAttr, defStyleRes))
    }

    private fun setup(typedArray: TypedArray) = typedArray.obtain {
        title.setCustomTextAppearance(getResourceId(R.styleable.SeeMoreItemView_android_textAppearance, 0))
    }

    override fun bind(model: Int) {
        title.text = model.toString()
    }

}