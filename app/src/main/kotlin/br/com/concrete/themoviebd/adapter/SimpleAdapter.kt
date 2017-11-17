package br.com.concrete.themoviebd.adapter

import android.content.Context
import android.view.View
import br.com.concrete.themoviebd.base.BaseRecyclerAdapter
import br.com.concrete.themoviebd.base.ViewBinder

class SimpleAdapter<MODEL, VIEW>(creator: (context: Context) -> VIEW) :
        BaseRecyclerAdapter<MODEL>({ context, _ -> creator.invoke(context) })
        where VIEW : View, VIEW : ViewBinder<MODEL>