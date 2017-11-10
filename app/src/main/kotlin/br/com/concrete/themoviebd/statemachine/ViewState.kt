package br.com.concrete.themoviebd.statemachine

import android.view.View

class ViewState {
    val visibles = mutableListOf<View>()
    val gones = mutableListOf<View>()
    val invisibles = mutableListOf<View>()
    var enter: (() -> Unit)? = null
        private set
    var exit: (() -> Unit)? = null
        private set

    fun onEnter(func: () -> Unit) {
        enter = func
    }

    fun onExit(func: () -> Unit) {
        exit = func
    }

    fun visibles(vararg views: View) {
        visibles += views
    }

    fun invisibles(vararg views: View) {
        invisibles += views
    }

    fun gones(vararg views: View) {
        gones += views
    }

}