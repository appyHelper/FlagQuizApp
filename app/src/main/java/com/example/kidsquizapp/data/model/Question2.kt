package com.example.kidsquizapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Question2 (
    val answer1: String?,
    val answer2: String?,
    val answer3: String?,
    val answer4: String?,
    val correctanswer: String?,
    val question: String?
) :Parcelable