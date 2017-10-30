package br.com.concrete.sdk

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import br.com.concrete.sdk.data.ResponseLiveData
import br.com.concrete.sdk.data.remote.apiInstance
import br.com.concrete.sdk.model.domain.ImageConfig
import br.com.concrete.sdk.model.domain.SdkConfig

object ConfigRepository {

    fun getConfiguration(): ResponseLiveData<SdkConfig> {
        return apiInstance.getConfiguration()
    }

    fun getImageConfiguration(): LiveData<ImageConfig> {
        return Transformations.map(getConfiguration()) { it.data?.images!! }
    }

    fun getChangeKeys(): LiveData<List<String>> {
        return Transformations.map(getConfiguration()) { it.data?.changeKeys!! }
    }

}