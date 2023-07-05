package com.monique.projetointegrador.features.moviedetails.data.model.certification

import com.google.gson.annotations.SerializedName

class CertificationResponse(
    @SerializedName("iso_3166_1")
    val region: String,
    @SerializedName("release_dates")
    val releaseDates: List<ReleaseDatesResponse>
)