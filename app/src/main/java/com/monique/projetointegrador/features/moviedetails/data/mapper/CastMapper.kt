package com.monique.projetointegrador.features.moviedetails.data.mapper

import com.monique.projetointegrador.features.moviedetails.data.model.cast.CastResponse
import com.monique.projetointegrador.features.moviedetails.domain.model.Cast

class CastMapper {
    fun map(castList: List<CastResponse>): List<Cast>{
        val celebrities = mutableListOf<Cast>()
        castList.forEach {
            val celebrity = Cast(
                name = it.name,
                profilePath = it.profilePath,
                character = it.character
            )
            celebrities.add(celebrity)
        }
        return celebrities
    }


}