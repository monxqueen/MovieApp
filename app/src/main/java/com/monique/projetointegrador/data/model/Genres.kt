package com.monique.projetointegrador.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Genres(val id: Int, val name: String): Parcelable
