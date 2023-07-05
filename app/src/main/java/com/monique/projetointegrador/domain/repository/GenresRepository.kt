package com.monique.projetointegrador.domain.repository

import com.monique.projetointegrador.domain.model.*
import kotlinx.coroutines.flow.Flow

interface GenresRepository {
    fun getAllGenres(): Flow<List<Genre>>
}