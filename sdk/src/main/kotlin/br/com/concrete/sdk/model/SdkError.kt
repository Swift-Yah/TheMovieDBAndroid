package br.com.concrete.sdk.model

import android.os.Parcel
import br.com.concrete.sdk.extension.KParcelable
import br.com.concrete.sdk.extension.parcelableCreator
import br.com.concrete.sdk.extension.readBoolean
import br.com.concrete.sdk.extension.writeBoolean
import com.google.gson.annotations.Expose

data class SdkError(
        @Expose val statusMessage: String = "",
        @Expose val statusCode: Int = 0,
        @Expose val success: Boolean = false,
        @Expose val errors: List<String> = emptyList()
) : KParcelable {
    companion object {
        @JvmField
        val CREATOR = parcelableCreator(::SdkError)
    }

    private constructor(source: Parcel) : this(
            source.readString(),
            source.readInt(),
            source.readBoolean(),
            source.createStringArrayList()
    )

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(statusMessage)
        writeInt(statusCode)
        writeBoolean(success)
        writeStringList(errors)
    }
}