package com.monique.projetointegrador.data.model.cast

import com.google.gson.annotations.SerializedName

class CastListResponse(
    @SerializedName("cast")
    val cast: List<CastResponse>
)