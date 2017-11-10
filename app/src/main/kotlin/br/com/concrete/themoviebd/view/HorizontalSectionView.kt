package br.com.concrete.themoviebd.view

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import android.content.res.TypedArray
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import br.com.concrete.sdk.data.ResponseLiveData
import br.com.concrete.sdk.model.Page
import br.com.concrete.themoviebd.R
import br.com.concrete.themoviebd.constants.STATE_EMPTY
import br.com.concrete.themoviebd.constants.STATE_ERROR
import br.com.concrete.themoviebd.constants.STATE_LOADING
import br.com.concrete.themoviebd.constants.STATE_SUCCESS
import br.com.concrete.themoviebd.delegate.viewProvider
import br.com.concrete.themoviebd.extension.obtain
import br.com.concrete.themoviebd.extension.setCustomTextAppearance
import br.com.concrete.themoviebd.statemachine.ViewStateMachine

class HorizontalSectionView : RelativeLayout {

    private val stateMachine = ViewStateMachine()

    private val title: TextView by viewProvider(R.id.section_title)
    private val recyclerView: RecyclerView by viewProvider(R.id.section_success)
    private val emptyView: TextView by viewProvider(R.id.section_empty)
    private val loadingView: ProgressBar by viewProvider(R.id.section_loading)
    private val errorView: Button by viewProvider(R.id.section_error)

    init {
        inflate(context, R.layout.view_horizontal_section, this)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        setupStateMachine()
    }

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, R.attr.horizontalContentViewStyle)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : this(context, attrs, defStyleAttr, 0)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        setup(context.obtainStyledAttributes(attrs, R.styleable.HorizontalSectionView, defStyleAttr, defStyleRes))
    }

    fun toLoadingState() = stateMachine.changeState(STATE_LOADING)

    fun toErrorState(throwable: Throwable) = stateMachine.changeState(STATE_ERROR)

    fun toEmptyState() = stateMachine.changeState(STATE_EMPTY)

    fun toSuccessState(page: Page<*>) = stateMachine.changeState(STATE_SUCCESS)

    fun <T> observe(lifecycleOwner: LifecycleOwner, liveData: ResponseLiveData<Page<T>>) {
        liveData.observeError(lifecycleOwner, this::toErrorState)
        liveData.observeLoading(lifecycleOwner) { if (it) toLoadingState() }
        liveData.observeData(lifecycleOwner) {
            if (it.results.isEmpty()) toEmptyState()
            else toSuccessState(it)
        }
    }

    fun setOnRetryClickListener(onRetryClick: (View) -> Unit) {
        errorView.setOnClickListener(onRetryClick)
    }

    private fun setup(typedArray: TypedArray) = typedArray.obtain {
        title.setCustomTextAppearance(getResourceId(R.styleable.HorizontalSectionView_sectionTitleTextAppearance, 0))
        title.text = getString(R.styleable.HorizontalSectionView_sectionTitle)
    }

    private fun setupStateMachine() = stateMachine.setup(STATE_EMPTY) {
        add(STATE_LOADING) {
            visibles(loadingView)
            gones(recyclerView, emptyView, errorView)
        }
        add(STATE_SUCCESS) {
            visibles(recyclerView)
            gones(loadingView, emptyView, errorView)
        }
        add(STATE_EMPTY) {
            visibles(emptyView)
            gones(recyclerView, loadingView, errorView)
        }
        add(STATE_ERROR) {
            visibles(errorView)
            gones(recyclerView, emptyView, loadingView)
        }
    }

}