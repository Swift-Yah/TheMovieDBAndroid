package br.com.concrete.themoviebd.base

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

abstract class BaseViewHolder<in T>(inflater: LayoutInflater, parent: ViewGroup, @LayoutRes layoutId: Int) :
        RecyclerView.ViewHolder(inflater.inflate(layoutId, parent, false)) {

    abstract fun bind(model: T)

}