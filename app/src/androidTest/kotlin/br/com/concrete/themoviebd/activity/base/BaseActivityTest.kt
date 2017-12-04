package br.com.concrete.themoviebd.activity.base

import android.support.test.rule.ActivityTestRule
import br.com.concrete.themoviebd.base.BaseActivity
import br.com.concrete.themoviebd.base.BaseViewModel
import br.com.concrete.themoviebd.delegate.ViewModelProviderDelegate
import br.com.concrete.themoviebd.extension.mockViewModel
import br.com.concrete.themoviebd.feature.main.MainViewModel
import com.nhaarman.mockito_kotlin.whenever
import net.vidageek.mirror.dsl.Mirror
import org.junit.Before
import org.junit.Rule
import org.mockito.MockitoAnnotations
import org.mockito.Spy
import kotlin.reflect.KClass

open class BaseActivityTest<AC : BaseActivity>(activityClass: KClass<AC>) {

    @Rule
    @JvmField
    val rule = ActivityTestRule(activityClass.java, false, false)

    @Spy
    private lateinit var delegateCompanion: ViewModelProviderDelegate.Companion

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        // Set mocked dependency
        Mirror().on(ViewModelProviderDelegate::class.java).set().field("Companion").withValue(delegateCompanion)
    }

    fun <VM : BaseViewModel> mockViewModelForClass(viewModelClass: KClass<VM>): VM {
        val viewModel = mockViewModel(viewModelClass)

        // Create "mocked" Delegate
        val delegate = ViewModelProviderDelegate(viewModelClass, false)
        delegate.viewModel = viewModel

        // Add rule to return the mocked delegate when the create method is called
        whenever(delegateCompanion.create(viewModelClass, false)).thenReturn(delegate)

        return viewModel
    }
}