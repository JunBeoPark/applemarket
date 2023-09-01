package com.android.applemarket

import android.os.Parcelable


@Parcelize
data class Itemdata(
    val Image: Int,
    val ItemTitle: String,
    val ItemDetail: String,
    val SellerName: String,
    val Price: Int,
    val Address: String,
    var InterestCnt:Int,
    val ChatCnt: Int,
    var isLike: Boolean
) : Parcelable