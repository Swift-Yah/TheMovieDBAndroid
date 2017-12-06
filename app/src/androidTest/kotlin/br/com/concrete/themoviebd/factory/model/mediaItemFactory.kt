package br.com.concrete.themoviebd.factory.model

import br.com.concrete.themoviebd.sdk.model.Page
import br.com.concrete.themoviebd.model.MediaItem
import br.com.concrete.themoviebd.model.mediaItemFrom

fun randomMediaItem(): MediaItem {
    return mediaItemFrom(randomMovie())
}

fun mediaItemList(): List<MediaItem> {
    return arrayListOf(randomMediaItem())
}

fun mediaItemPage(): Page<MediaItem> {
    return pageWith(mediaItemList())
}