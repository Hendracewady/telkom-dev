package com.telkom.android.telkomdevtest.model

data class CommentResponse(
	val parent: Int,
	val by: String,
	val id: Int,
	val text: String,
	val time: Int,
	val type: String,
	val kids: List<Int>
)

