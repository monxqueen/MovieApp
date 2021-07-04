package com.monique.projetointegrador

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movies(private var img: String = "",
                  private val title: String?,
                  private val rate: String?,
                  private val genre: Array<String>? = arrayOf(),
                  private var isFavorite: Boolean = false): Parcelable {
    fun getTitle() = title
    fun getRate() = rate
    fun getImg() = img
    fun getIsFavorite() = isFavorite
    fun getGenre() = genre
    fun setIsFavorite(trueOrFalse: Boolean){
        isFavorite = trueOrFalse
    }

}

