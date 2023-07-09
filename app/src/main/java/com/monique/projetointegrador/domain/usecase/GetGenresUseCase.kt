package com.monique.projetointegrador.domain.usecase

import com.monique.projetointegrador.domain.model.Genre
import kotlinx.coroutines.flow.Flow

interface GetGenresUseCase {
    fun executeGenres(): Flow<List<Genre>>
}