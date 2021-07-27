package com.monique.projetointegrador.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val imgHome: String? = null,
    val id: Int,
    val title: String? = null,
    val rating: String? = null,
    val genres: List<Int>,
    var isFavorite: Boolean = false,
) : Parcelable

