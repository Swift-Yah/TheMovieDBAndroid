package br.com.concrete.themoviebd.activity.base

import android.support.v7.app.AppCompatActivity
import br.com.concrete.themoviebd.extension.toast

abstract class BaseActivity : AppCompatActivity() {

    open fun onErrorReceived(throwable: Throwable) {
        toast(throwable.toString())
    }

}