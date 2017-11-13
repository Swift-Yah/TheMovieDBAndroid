package br.com.concrete.themoviebd

import android.app.Application
import android.content.Context
import android.support.test.runner.AndroidJUnitRunner
import com.github.tmurakami.dexopener.DexOpener

class CustomTestRunner : AndroidJUnitRunner() {

    override fun newApplication(cl: ClassLoader, className: String, context: Context): Application {
        DexOpener.builder(context)
                .buildConfig(BuildConfig::class.java)
                .build()
                .installTo(cl)
        return super.newApplication(cl, className, context)
    }

}