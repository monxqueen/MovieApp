package com.monique.projetointegrador.data.mappers

import com.monique.projetointegrador.data.model.certification.ReleaseDatesResponse
import com.monique.projetointegrador.domain.model.Certification

class CertificationMapper {
    fun map(certificationList: List<ReleaseDatesResponse>?): List<Certification>{
        val certifications = mutableListOf<Certification>()
        certificationList?.let {
            certificationList.forEach {
                val certification = Certification(
                    certification = it.certification,
                    type = it.type
                )
                certifications.add(certification)
            }
        }
        return certifications
    }
}