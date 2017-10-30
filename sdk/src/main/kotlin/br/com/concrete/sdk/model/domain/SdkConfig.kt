package br.com.concrete.sdk.model.domain

import android.os.Parcel
import br.com.concrete.sdk.extension.KParcelable
import br.com.concrete.sdk.extension.parcelableCreator
import br.com.concrete.sdk.extension.readTypedObjectCompat
import br.com.concrete.sdk.extension.writeTypedObjectCompat
import com.google.gson.annotations.Expose

data class SdkConfig(
        @Expose val images: ImageConfig,
        @Expose val changeKeys: List<String>
) : KParcelable {
    companion object {
        @JvmField
        val CREATOR = parcelableCreator(::SdkConfig)
    }

    private constructor(source: Parcel) : this(
            source.readTypedObjectCompat(ImageConfig.CREATOR)!!,
            source.createStringArrayList()
    )

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeTypedObjectCompat(images, flags)
        writeStringList(changeKeys)
    }
}

