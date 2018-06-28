package com.zhukovme.flickrclient.ui.common

import android.os.Parcel
import android.os.Parcelable
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion

/**
 * Created by Michael Zhukov on 28.06.2018.
 * email: zhukovme@gmail.com
 */
class StringSuggestion(private val text: String) : SearchSuggestion {

    companion object CREATOR : Parcelable.Creator<StringSuggestion> {
        override fun createFromParcel(parcel: Parcel): StringSuggestion = StringSuggestion(parcel)
        override fun newArray(size: Int): Array<StringSuggestion?> = arrayOfNulls(size)
    }

    constructor(parcel: Parcel) : this(parcel.readString())

    override fun getBody(): String = text

    override fun writeToParcel(parcel: Parcel, flags: Int) = parcel.writeString(text)

    override fun describeContents(): Int = 0
}
