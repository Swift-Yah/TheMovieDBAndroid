package br.com.concrete.themoviebd.adapter.base

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import br.com.concrete.themoviebd.adapter.holder.base.BaseViewHolder

open class BaseRecyclerAdapter<MODEL, HOLDER : BaseViewHolder<MODEL>> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val items: MutableList<MODEL> = mutableListOf()
    private var layoutInflater: LayoutInflater? = null

    var holderCreator: ((inflater: LayoutInflater, parent: ViewGroup) -> HOLDER)? = null

    open fun setList(items: List<MODEL>) {
        val listSize = this.items.size
        this.items.clear()
        notifyItemRangeRemoved(0, listSize)
        addAll(items)
    }

    open fun addAll(newItems: List<MODEL>) {
        val start = itemCount
        this.items.addAll(newItems)
        notifyItemRangeInserted(start, newItems.size)
    }

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            holderCreator?.invoke(getLayoutInflater(parent), parent) ?: throw IllegalStateException("LayoutByType must be set")

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? BaseViewHolder<MODEL>)?.bind(items[position])
    }

    private fun getLayoutInflater(parent: ViewGroup): LayoutInflater {
        if (layoutInflater == null)
            layoutInflater = LayoutInflater.from(parent.context)
        return layoutInflater!!
    }
}

