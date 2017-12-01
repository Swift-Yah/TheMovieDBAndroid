package br.com.concrete.themoviebd.base

import android.arch.lifecycle.Lifecycle.Event.*
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LifecycleRegistry
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v4.app.Fragment
import android.view.View

open class ViewLifecycleFragment : Fragment() {

    private class ViewLifecycleOwner : LifecycleOwner {
        override fun getLifecycle(): LifecycleRegistry = LifecycleRegistry(this)
    }

    private var viewLifecycleOwner: ViewLifecycleOwner? = null

    fun getLifecycleOwner(): LifecycleOwner {
        return viewLifecycleOwner ?: throw IllegalStateException("Wooow!! Owner not initialized!")
    }

    override fun onViewCreated(view: View, @Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner = ViewLifecycleOwner()
        viewLifecycleOwner!!.lifecycle.handleLifecycleEvent(ON_CREATE)
    }

    override fun onStart() {
        super.onStart()
        viewLifecycleOwner?.lifecycle?.handleLifecycleEvent(ON_START)
    }

    override fun onResume() {
        super.onResume()
        viewLifecycleOwner?.lifecycle?.handleLifecycleEvent(ON_RESUME)
    }

    override fun onPause() {
        viewLifecycleOwner?.lifecycle?.handleLifecycleEvent(ON_PAUSE)
        super.onPause()
    }

    override fun onStop() {
        viewLifecycleOwner?.lifecycle?.handleLifecycleEvent(ON_STOP)
        super.onStop()
    }

    override fun onDestroyView() {
        viewLifecycleOwner?.lifecycle?.handleLifecycleEvent(ON_DESTROY)
        viewLifecycleOwner = null
        super.onDestroyView()
    }
}
