package com.jrs.httpexample.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize as Parcelize

data class ResponseUsers(@SerializedName("results") val responseUsers: List<User>)
@Parcelize
data class User(val name: Name, val dob: Dob, val picture: Picture, val email: String, val phone:String):Parcelable{}
@Parcelize
data class Name(val title: String, val first: String, val last: String):Parcelable{}
@Parcelize
data class Dob(val date: String, val age: String):Parcelable{}
@Parcelize
data class Picture(val large: String, val medium: String, val thumbnail: String):Parcelable{}
