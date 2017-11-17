package br.com.concrete.themoviebd.base

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup

open class BaseRecyclerAdapter<MODEL>(private val viewCreator: ((context: Context, viewType: Int) -> ViewBinder<*>?))
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val items = mutableListOf<MODEL>()
    var onItemClick: ((MODEL) -> Unit)? = null

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewBinder = viewCreator.invoke(parent.context, viewType) ?: throw IllegalStateException("Unable create view with type: $viewType")
        val itemView = viewBinder as? View ?: throw IllegalStateException("The ViewBinder instance also must be a View")
        return BaseViewHolder(itemView)
    }

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder.itemView as? ViewBinder<MODEL>)?.bind(items[position])
        bindHolder(holder.itemView as? ViewBinder<MODEL>, items[position])
        onItemClick?.let { listener ->
            holder.itemView.setOnClickListener { listener.invoke(items[position]) }
        }
    }

    fun <T> bindHolder(binder: ViewBinder<T>?, model: T) {
        binder?.bind(model)
    }

    open fun setList(newList: List<MODEL>) {
        val listSize = items.size
        items.clear()
        notifyItemRangeRemoved(0, listSize)
        addAll(newList)
    }

    open fun addAll(newItems: List<MODEL>) {
        val start = itemCount
        this.items.addAll(newItems)
        notifyItemRangeInserted(start, newItems.size)
    }

    fun withListener(onItemClick: (MODEL) -> Unit): BaseRecyclerAdapter<MODEL> {
        this.onItemClick = onItemClick
        return this
    }

}

class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view)

interface ViewBinder<in MODEL> {
    fun bind(model: MODEL)
}