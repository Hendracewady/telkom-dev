package com.telkom.android.telkomdevtest.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserItemResponse(
	val score: Int,
	val by: String,
	val id: Long,
	val time: Int,
	val title: String,
	val type: String,
	val descendants: Int,
	val url: String,
	val kids: ArrayList<Long>
) : Parcelable

