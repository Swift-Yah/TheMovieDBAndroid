package br.com.concrete.themoviebd.sdk

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations.map
import br.com.concrete.themoviebd.sdk.data.remote.apiInstance
import br.com.concrete.themoviebd.sdk.extension.toSimpleLiveData
import br.com.concrete.themoviebd.sdk.model.domain.ImageConfig
import br.com.concrete.themoviebd.sdk.model.domain.SdkConfig

object ConfigRepository {

    private val configurationLiveData = apiInstance.configuration()

    fun getConfiguration() = configurationLiveData.toSimpleLiveData()

    fun getImageConfiguration(): LiveData<ImageConfig> = map(getConfiguration(), SdkConfig::images)

    fun getChangeKeys(): LiveData<List<String>> = map(getConfiguration(), SdkConfig::changeKeys)

}