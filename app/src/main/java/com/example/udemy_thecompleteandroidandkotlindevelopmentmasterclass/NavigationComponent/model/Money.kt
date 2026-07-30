package com.example.udemy_thecompleteandroidandkotlindevelopmentmasterclass.NavigationComponent.model

import android.os.Parcel
import android.os.Parcelable
import java.math.BigDecimal

data class Money(val amount: BigDecimal): Parcelable{
    constructor(parcel: Parcel):this(BigDecimal(0))
    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        TODO("Not yet implemented")
    }

    companion object CREATOR: Parcelable.Creator<Money>{
        override fun createFromParcel(source: Parcel?): Money? {
            TODO("Not yet implemented")
        }

        override fun newArray(size: Int): Array<out Money?>? {
            TODO("Not yet implemented")
        }
    }

}
