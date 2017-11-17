package br.com.concrete.themoviebd.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import br.com.concrete.sdk.model.Page
import br.com.concrete.themoviebd.TYPE_ITEM
import br.com.concrete.themoviebd.TYPE_SEE_MORE
import br.com.concrete.themoviebd.base.BaseRecyclerAdapter
import br.com.concrete.themoviebd.base.ViewBinder
import br.com.concrete.themoviebd.view.item.SeeMoreItemView

class SinglePageAdapter<MODEL, VIEW>(viewCreator: (context: Context) -> VIEW?) : BaseRecyclerAdapter<MODEL>(
        { context, viewType ->
            when (viewType) {
                TYPE_ITEM -> viewCreator.invoke(context)
                TYPE_SEE_MORE -> SeeMoreItemView(context)
                else -> null
            }
        }) where VIEW : View, VIEW : ViewBinder<MODEL> {

    var total: Int = 0

    override fun getItemCount() = items.size + 1

    override fun getItemViewType(position: Int) =
            if (position == items.size) TYPE_SEE_MORE else TYPE_ITEM

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            TYPE_ITEM -> super.onBindViewHolder(holder, position)
            TYPE_SEE_MORE -> bindHolder(holder.itemView as? ViewBinder<Int>, total)
        }
    }

    fun setPage(page: Page<MODEL>) {
        total = page.totalResults
        setList(page.results)
    }

}