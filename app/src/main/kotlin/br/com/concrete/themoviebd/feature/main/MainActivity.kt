package br.com.concrete.themoviebd.feature.main

import android.os.Bundle
import android.support.annotation.IdRes
import android.support.design.widget.BottomNavigationView
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import br.com.concrete.themoviebd.R
import br.com.concrete.themoviebd.base.BaseActivity
import br.com.concrete.themoviebd.base.BaseFragment
import br.com.concrete.themoviebd.delegate.viewModelProvider
import br.com.concrete.themoviebd.delegate.viewProvider
import br.com.concrete.themoviebd.extension.changeToFragment
import br.com.concrete.themoviebd.extension.clearFragmentStack
import br.com.concrete.themoviebd.extension.findFragment

class MainActivity : BaseActivity() {

    private val viewModel by viewModelProvider(MainViewModel::class)

    private val toolbar: Toolbar by viewProvider(R.id.toolbar)
    private val navigation: BottomNavigationView by viewProvider(R.id.main_navigation)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        navigation.selectedItemId = viewModel.selectedItemId
        navigation.setOnNavigationItemSelectedListener(this::onNavigationItemSelected)

        // Set a initial content
        selectTab(viewModel.selectedItemId)
    }

    override fun onBackPressed() {
        val fragment: BaseFragment? = findFragment(R.id.main_content)
        if (fragment?.handleBack() == true) return

        // Already in Home. Finish
        if (viewModel.shouldFinish()) {
            finish()
            return
        }

        // Not in home. Navigate to Home after handling back in the fragment
        if (supportFragmentManager.backStackEntryCount > 0) super.onBackPressed()
        else navigation.selectedItemId = viewModel.initialItemId
    }

    private fun onNavigationItemSelected(item: MenuItem): Boolean {
        clearFragmentStack()
        selectTab(item.itemId)
        return true
    }

    private fun selectTab(@IdRes itemId: Int) {
        changeToFragment(
                tag = viewModel.fragmentTagById(itemId),
                parentId = R.id.main_content,
                creator = viewModel::createFragmentByTag)
        viewModel.selectedItemId = itemId
    }

}