package br.com.concrete.themoviebd.activity.base

import android.content.Intent
import android.support.test.rule.ActivityTestRule
import br.com.concrete.sdk.extension.observe
import br.com.concrete.themoviebd.base.BaseActivity
import br.com.concrete.themoviebd.base.BaseFragment
import br.com.concrete.themoviebd.base.BaseViewModel
import br.com.concretesolutions.kappuccino.utils.doWait

open class BaseRobot<in T : BaseActivity, out VM : BaseViewModel>(private val rule: ActivityTestRule<T>, val viewModel: VM) {

    fun launchActivity(intent: Intent = Intent()) {
        rule.launchActivity(intent)
        doWait(200L)
//        registerDefaultErrors(rule.activity)
    }

    fun launchFragment(fragment: BaseFragment) {
        rule.launchActivity(Intent())
        doWait(200L)
        registerDefaultErrors(fragment)
    }

    open fun registerDefaultErrors(activity: BaseActivity) {
        viewModel.errorLiveData.observe(activity, activity::onErrorReceived)
    }

    open fun registerDefaultErrors(fragment: BaseFragment) {
        viewModel.errorLiveData.observe(fragment, fragment::onErrorReceived)
    }

}