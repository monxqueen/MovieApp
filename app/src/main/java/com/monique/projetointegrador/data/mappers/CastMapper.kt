package com.monique.projetointegrador.data.mappers

import com.monique.projetointegrador.data.model.cast.CastResponse
import com.monique.projetointegrador.domain.Cast

class CastMapper {
    fun map(castList: List<CastResponse>): List<Cast>{
        val celebrities = mutableListOf<Cast>()
        castList.forEach {
            val celebrity = Cast(
                name = it.name,
                profile_path = it.profile_path,
                character = it.character
            )
            celebrities.add(celebrity)
        }
        return celebrities
    }


}