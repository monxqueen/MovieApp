package com.monique.projetointegrador.data.mappers

import com.monique.projetointegrador.data.model.cast.CastResponse
import com.monique.projetointegrador.domain.model.Cast

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