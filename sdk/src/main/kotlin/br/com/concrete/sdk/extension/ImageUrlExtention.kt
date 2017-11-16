package br.com.concrete.sdk.extension

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations.map
import br.com.concrete.sdk.ConfigRepository
import br.com.concrete.sdk.model.type.*

fun imageUrlFor(@ImageSize size: Long, @ImageType type: Long, path: String?): LiveData<String> {
    return map(ConfigRepository.getImageConfiguration()) {
        val imageSize = when (type) {
            BACKDROP -> imageSizeFor(size, it.backdropSizes)
            LOGO -> imageSizeFor(size, it.logoSizes)
            POSTER -> imageSizeFor(size, it.posterSizes)
            PROFILE -> imageSizeFor(size, it.profileSizes)
            STILL -> imageSizeFor(size, it.stillSizes)
            else -> throw IllegalStateException("Type: $type is not supported")
        }
        "${it.secureBaseUrl}$imageSize$path"
    }
}

private fun imageSizeFor(@ImageSize size: Long, sizes: List<String>): String {
    if (sizes.isEmpty()) throw IllegalStateException("No sizes available for that configuration")

    return when (size) {
        ORIGINAL -> sizes[sizes.lastIndex]
        LARGE -> sizes[sizes.lastIndex]
        MEDIUM -> sizes[sizes.size / 2]
        SMALL -> sizes[0]
        else -> throw IllegalStateException("Size: $size is not supported")
    }
}

