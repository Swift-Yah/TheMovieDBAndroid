package br.com.concrete.themoviebd.activity.base

import android.content.Intent
import android.support.test.rule.ActivityTestRule
import br.com.concretesolutions.kappuccino.utils.doWait

open class BaseRobot<T : BaseActivity>(private val rule: ActivityTestRule<T>) {

    open fun launchActivity(intent: Intent = Intent()) {
        rule.launchActivity(intent)
        doWait(200L)
    }

}