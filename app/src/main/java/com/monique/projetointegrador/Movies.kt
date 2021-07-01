package com.monique.projetointegrador

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movies(private val title: String,
                  private val overview: String,
                  private val vote_average: Number ): Parcelable{

}

