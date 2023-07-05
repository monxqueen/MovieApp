package com.monique.projetointegrador.features.moviedetails.data.model.certification

import com.google.gson.annotations.SerializedName

class CertificationListReponse(@SerializedName("results") val results: List<CertificationResponse>)