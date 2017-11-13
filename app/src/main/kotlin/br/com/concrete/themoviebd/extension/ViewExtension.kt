@file:JvmName("ViewUtils")

package br.com.concrete.themoviebd.extension

import android.content.res.TypedArray
import android.os.Build
import android.support.annotation.StyleRes
import android.support.v7.app.ActionBar
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

fun View.addStatusBarPadding() {
    setPadding(paddingLeft,
            paddingTop + context.statusBarHeight(), paddingRight,
            paddingBottom)
}

fun View.addStatusBarMargin() {
    val params = layoutParams as ViewGroup.MarginLayoutParams
    params.topMargin += context.statusBarHeight()
}

fun ActionBar?.enableBack() {
    if (this == null) return
    setDisplayHomeAsUpEnabled(true)
    setDisplayShowHomeEnabled(true)
}

fun ImageView.loadUrl(url: String) {
    if (url.isEmpty()) return
    Picasso.with(context).load(url).into(this)
}

@Suppress("DEPRECATION")
fun TextView.setCustomTextAppearance(@StyleRes styleRes: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) setTextAppearance(styleRes)
    else setTextAppearance(context, styleRes)
}

inline fun TypedArray.obtain(func: TypedArray.() -> Unit) {
    func()
    recycle()
}