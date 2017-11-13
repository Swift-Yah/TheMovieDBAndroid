package br.com.concrete.sdk.extension

import br.com.concrete.sdk.ConfigRepository
import br.com.concrete.sdk.model.domain.Movie
import br.com.concrete.sdk.model.type.*

fun Movie.backdrop(@ImageSize size: Long) =
        imageUrlFor(size = size, type = POSTER, path = backdropPath)

fun Movie.poster(@ImageSize size: Long) =
        imageUrlFor(size = size, type = POSTER, path = posterPath)

private fun imageUrlFor(@ImageSize size: Long, @ImageType type: Long, path: String?): String {
    val imgConfig = ConfigRepository.getLocalImageConfiguration() ?: return ""
    val imageSize = when (type) {
        BACKDROP -> imageSizeFor(size, imgConfig.backdropSizes)
        LOGO -> imageSizeFor(size, imgConfig.logoSizes)
        POSTER -> imageSizeFor(size, imgConfig.posterSizes)
        PROFILE -> imageSizeFor(size, imgConfig.profileSizes)
        STILL -> imageSizeFor(size, imgConfig.stillSizes)
        else -> throw IllegalStateException("Type: $type is not supported")
    }
    return "${imgConfig.secureBaseUrl}$imageSize$path"
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

