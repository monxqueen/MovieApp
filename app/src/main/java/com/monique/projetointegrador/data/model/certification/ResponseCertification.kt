package com.monique.projetointegrador.data.model.certification

import com.google.gson.annotations.SerializedName
import com.monique.projetointegrador.data.model.certification.CertificationResponse

class ResponseCertification(@SerializedName("results") val results: List<CertificationResponse>)