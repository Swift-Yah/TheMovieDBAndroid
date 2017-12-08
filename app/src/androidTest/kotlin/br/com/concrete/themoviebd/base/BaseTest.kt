package br.com.concrete.themoviebd.base

import android.content.Intent
import android.support.test.espresso.intent.rule.IntentsTestRule
import br.com.concrete.themoviebd.TestActivity
import br.com.concrete.themoviebd.extension.mockRepository
import br.com.concrete.themoviebd.sdk.ConfigRepository
import br.com.concrete.themoviebd.sdk.MovieRepository
import br.com.concrete.themoviebd.sdk.TVShowRepository
import org.junit.Before
import org.junit.Rule
import kotlin.reflect.KClass

open class BaseActivityTest<AC : BaseActivity>(activityClass: KClass<AC>) {

    @Rule
    @JvmField
    val rule = IntentsTestRule(activityClass.java, true, false)

    @Before
    fun setup() {
        mockRepository(MovieRepository::class.java)
        mockRepository(ConfigRepository::class.java)
        mockRepository(TVShowRepository::class.java)
    }

}

open class BaseFragmentTest : BaseActivityTest<TestActivity>(TestActivity::class) {
    @Before
    fun launchActivity() {
        rule.launchActivity(Intent())
    }
}

open class BaseViewTest : BaseActivityTest<TestActivity>(TestActivity::class) {
    @Before
    fun launchActivity() {
        rule.launchActivity(Intent())
    }
}