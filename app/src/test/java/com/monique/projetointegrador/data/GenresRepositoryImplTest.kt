package com.monique.projetointegrador.data

import app.cash.turbine.test
import com.monique.projetointegrador.data.mappers.GenreMapper
import com.monique.projetointegrador.data.model.genres.GenreResponse
import com.monique.projetointegrador.data.model.genres.GenresListResponse
import com.monique.projetointegrador.data.remotesource.GenresApi
import com.monique.projetointegrador.data.remotesource.GenresRemoteDataSource
import com.monique.projetointegrador.data.repository.GenresRepositoryImpl
import com.monique.projetointegrador.domain.model.Genre
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsInstanceOf
import org.junit.Test
import kotlin.time.ExperimentalTime

@ExperimentalTime
class GenresRepositoryImplTest {
    private val genreMapper: GenreMapper = mockk()
    private val genresRemoteDataSource: GenresRemoteDataSource = mockk()
    private val repository = GenresRepositoryImpl(
        genreMapper = genreMapper,
        genresRemoteDataSource = genresRemoteDataSource
    )

    @Test
    fun `getAllGenres should return a mapped genres list`() = runBlocking {
        // Given
        val responseGenresList = GenresListResponse(
            genres = listOf(
                GenreResponse(id = 1, genreName = "romance"),
                GenreResponse(id = 1, genreName = "action"),
                GenreResponse(id = 1, genreName = "thriller")
            )
        )
        val expectedResult = listOf(
            Genre(id = 1, name = "romance"),
            Genre(id = 1, name = "action"),
            Genre(id = 1, name = "thriller")
        )

        every { genresRemoteDataSource.getAllGenres() } returns flow { emit(responseGenresList) }
        every { genreMapper.map(responseGenresList.genres) } returns expectedResult

        // When
        val response = repository.getAllGenres()

        // Then
        response.test {
            assertEquals(expectedResult, expectItem())
            expectComplete()
        }
    }

    @Test
    fun `getAllGenres should return a throwable if dataset returns a throwable`() = runBlocking {
        // Given
        val expectedError = Throwable()

        every { genresRemoteDataSource.getAllGenres() } returns flow { throw expectedError }

        // When
        val response = repository.getAllGenres()

        // Then
        response.test {
            val error = expectError()
            assertThat(error, IsInstanceOf(Throwable::class.java))
            assertEquals(expectedError, error)
        }
    }
}