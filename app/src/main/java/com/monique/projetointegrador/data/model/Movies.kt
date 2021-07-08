package com.monique.projetointegrador.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movies(@SerializedName("poster_path")
                  private var img: String? = null,
                  private var id: Int? = null,
                  private val title: String?,
                  @SerializedName("vote_average")
                  private val rating: String?, //type is actually Number
                  private var genres: Array<Genres>? = null,
                  private var release_date: Int? = null,
                  private var isFavorite: Boolean = false): Parcelable {
    fun getTitle() = title
    fun getRating() = rating
    fun getImg() = img
    fun getIsFavorite() = isFavorite
    fun getGenre() = genres
    fun setIsFavorite(trueOrFalse: Boolean){
        isFavorite = trueOrFalse
    }

}

