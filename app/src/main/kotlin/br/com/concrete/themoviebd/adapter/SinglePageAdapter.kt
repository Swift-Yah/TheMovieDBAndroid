package br.com.concrete.themoviebd.adapter

import android.content.Context
import android.view.View
import br.com.concrete.themoviebd.sdk.model.Page
import br.com.concrete.themoviebd.TYPE_ITEM
import br.com.concrete.themoviebd.TYPE_SEE_MORE
import br.com.concrete.themoviebd.base.BaseRecyclerAdapter
import br.com.concrete.themoviebd.base.BaseViewHolder
import br.com.concrete.themoviebd.base.ViewBinder
import br.com.concrete.themoviebd.view.item.SeeMoreItemView

class SinglePageAdapter<MODEL, VIEW>(viewCreator: (context: Context) -> VIEW) : BaseRecyclerAdapter<MODEL>(
        { context, viewType ->
            when (viewType) {
                TYPE_ITEM -> viewCreator.invoke(context)
                TYPE_SEE_MORE -> SeeMoreItemView(context)
                else -> null
            }
        }) where VIEW : View, VIEW : ViewBinder<MODEL> {

    private var total: Int = 0
        set(value) {
            field = value
            notifyItemChanged(items.size)
        }

    var onSeeMoreClick: ((View) -> Unit)? = null

    override fun getItemCount() = items.size + 1

    override fun getItemViewType(position: Int) =
            if (position == items.size) TYPE_SEE_MORE else TYPE_ITEM

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when (getItemViewType(position)) {
            TYPE_ITEM -> super.onBindViewHolder(holder, position)
            TYPE_SEE_MORE -> {
                bindHolder(holder, total)
                holder.itemView.setOnClickListener(onSeeMoreClick)
            }
        }
    }

    fun setPage(page: Page<MODEL>) {
        setList(page.results)
        total = page.totalResults - page.results.size
    }

}