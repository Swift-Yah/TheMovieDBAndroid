package br.com.concrete.themoviebd.adapter

import br.com.concrete.sdk.model.Page
import br.com.concrete.themoviebd.base.BaseRecyclerAdapter
import br.com.concrete.themoviebd.adapter.holder.MediaItemViewHolder
import br.com.concrete.themoviebd.model.MediaItem

private const val TYPE_NORMAL = 1
private const val TYPE_SEE_MORE = 2

class MediaItemRecyclerAdapter : BaseRecyclerAdapter<MediaItem, MediaItemViewHolder>() {

    private var totalItems = 0

    fun setPage(page: Page<MediaItem>) {
        totalItems = page.totalResults - page.results.size
        setList(page.results)
    }

//    override fun getItemCount() = super.getItemCount() + 1

    override fun getItemViewType(position: Int) =
            if (position == items.size) TYPE_SEE_MORE
            else TYPE_NORMAL
}