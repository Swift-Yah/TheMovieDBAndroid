package br.com.concrete.themoviebd.base

import android.content.Intent
import android.support.test.rule.ActivityTestRule
import android.view.View
import br.com.concrete.themoviebd.TestActivity
import br.com.concretesolutions.kappuccino.utils.doWait

open class BaseActivityRobot<T : BaseActivity>(val rule: ActivityTestRule<T>) {

    fun launchActivity(intent: Intent = Intent()) {
        rule.launchActivity(intent)
        doWait(200L)
    }

}

open class BaseFragmentRobot(rule: ActivityTestRule<TestActivity>) : BaseActivityRobot<TestActivity>(rule) {

    fun launchFragment(fragment: BaseFragment) {
        rule.activity?.setFragment(fragment)
        doWait(200L)
    }

}

open class BaseViewRobot(rule: ActivityTestRule<TestActivity>) : BaseActivityRobot<TestActivity>(rule) {

    fun launchView(view: View) {
        rule.activity?.setView(view)
    }

}