package com.fixedsolutions.fixedsolutionstask.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class ComplaintModel(
    val title: String,
    val description: String
) : Parcelable