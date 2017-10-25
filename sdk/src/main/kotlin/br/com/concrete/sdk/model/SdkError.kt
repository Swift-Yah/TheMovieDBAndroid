package br.com.concrete.sdk.model

import android.os.Parcel
import br.com.concrete.sdk.extension.KParcelable
import br.com.concrete.sdk.extension.parcelableCreator
import com.google.gson.annotations.Expose

data class SdkError(
        @Expose val statusMessage: String,
        @Expose val statusCode: Int
) : KParcelable {
    companion object {
        @JvmField
        val CREATOR = parcelableCreator(::SdkError)
    }

    private constructor(source: Parcel) : this(
            source.readString(),
            source.readInt()
    )

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(statusMessage)
        writeInt(statusCode)
    }
}