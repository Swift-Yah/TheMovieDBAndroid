package br.com.concrete.sdk

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations.map
import br.com.concrete.sdk.data.ResponseLiveData
import br.com.concrete.sdk.data.remote.apiInstance
import br.com.concrete.sdk.model.domain.ImageConfig
import br.com.concrete.sdk.model.domain.SdkConfig

object ConfigRepository {

    private val configurationLiveData = apiInstance.getConfiguration().apply { invalidate() }

    fun getConfiguration(): ResponseLiveData<SdkConfig> {
        return configurationLiveData
    }

    fun getImageConfiguration(): LiveData<ImageConfig> {
        return map(configurationLiveData) { it.data?.images!! }
    }

    fun getChangeKeys(): LiveData<List<String>> {
        return map(configurationLiveData) { it.data?.changeKeys!! }
    }

    fun getLocalImageConfiguration(): ImageConfig? {
        return configurationLiveData.getData()?.images
    }

}