package com.monique.projetointegrador.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
class Movies(@SerializedName("poster_path")
                  private var img: String? = null,
                  private var id: Int? = null,
                  private var title: String? = null,
                  @SerializedName("vote_average")
                  private var rating: String? = null, //type is actually Numbe
                  private var genres: List<Genres>? = null,
                  private var release: Int? = null,
                  private var isFavorite: Boolean = false): Parcelable {
    fun getTitle() = title
    fun getRating() = rating
    fun getImg() = img
    fun getIsFavorite() = isFavorite
    fun getGenres() = genres
    fun setIsFavorite(trueOrFalse: Boolean){
        isFavorite = trueOrFalse
    }
    fun showRating() = "$rating%"

}

