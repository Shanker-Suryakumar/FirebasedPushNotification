package com.example.firebasenotificationapp

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class NotificationModel() : Parcelable {
    @SerializedName("to")
    @Expose
    var mTo: String = ""

    @SerializedName("data")
    @Expose
    var mData: String = ""

    @SerializedName("extra_information")
    @Expose
    var mExtraInformation: String = ""

    @SerializedName("notification")
    @Expose
    var mNotification: String = ""

    @SerializedName("title")
    @Expose
    var mTitle: String = ""

    @SerializedName("text")
    @Expose
    var mText: String = ""

    @SerializedName("click_action")
    @Expose
    var mClickAction: String = ""

    constructor(parcel: Parcel) : this() {
        this.mTo = parcel.readString().toString()
        this.mData = parcel.readString().toString()
        this.mExtraInformation = parcel.readString().toString()
        this.mNotification = parcel.readString().toString()
        this.mTitle = parcel.readString().toString()
        this.mText = parcel.readString().toString()
        this.mClickAction = parcel.readString().toString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(this.mTo)
        parcel.writeString(this.mData)
        parcel.writeString(this.mExtraInformation)
        parcel.writeString(this.mNotification)
        parcel.writeString(this.mTitle)
        parcel.writeString(this.mText)
        parcel.writeString(this.mClickAction)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return "NotificationModel(mTo='$mTo', mData='$mData', mExtraInformation='$mExtraInformation', mNotification='$mNotification', mTitle='$mTitle', mText='$mText', mClickAction='$mClickAction')"
    }

    companion object CREATOR : Parcelable.Creator<NotificationModel> {
        override fun createFromParcel(parcel: Parcel): NotificationModel {
            return NotificationModel(parcel)
        }

        override fun newArray(size: Int): Array<NotificationModel?> {
            return arrayOfNulls(size)
        }
    }
}
