package br.com.concrete.sdk.model.type

import android.support.annotation.IntDef

@IntDef(ORIGINAL, LARGE, MEDIUM, SMALL)
@Retention(AnnotationRetention.SOURCE)
annotation class ImageSize

@IntDef(BACKDROP, LOGO, POSTER, PROFILE, STILL)
@Retention(AnnotationRetention.SOURCE)
annotation class ImageType