package br.com.concrete.themoviebd

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import br.com.concrete.themoviebd.base.BaseActivity
import br.com.concrete.themoviebd.base.BaseFragment
import br.com.concrete.themoviebd.extension.fragmentTransaction

class TestActivity : BaseActivity() {

    private lateinit var content: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        content = FrameLayout(this)
        content.setBackgroundColor(Color.WHITE)
        content.id = android.R.id.list_container
        setContentView(content)
    }

    fun setFragment(fragment: BaseFragment) {
        fragmentTransaction { add(android.R.id.list_container, fragment, "TAG") }
    }

    fun setView(view: View) {
        content.addView(view)
    }
}
