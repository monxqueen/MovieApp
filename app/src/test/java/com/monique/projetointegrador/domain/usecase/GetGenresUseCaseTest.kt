package com.monique.projetointegrador.domain.usecase

import app.cash.turbine.test
import com.monique.projetointegrador.domain.model.Genre
import com.monique.projetointegrador.domain.repository.GenresRepository
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsInstanceOf
import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.time.ExperimentalTime

@ExperimentalTime
class GetGenresUseCaseTest {
    private val repository: GenresRepository = mockk()
    private val useCase = GetGenresUseCase(repository)

    @Test
    fun `executeGenres function should return genre list when repository returns success`() = runBlocking {
        // Given
        val expectedGenresList = listOf(
            Genre(id = 1, name = "action"),
            Genre(id = 1, name = "romance")
        )

        every { repository.getAllGenres() } returns flow { emit(expectedGenresList) }

        // When
        val result = useCase.executeGenres()

        // Then
        result.test {
            assertEquals(expectedGenresList, expectItem())
            expectComplete()
        }

    }

    @Test
    fun `executeGenres function should return error when repository returns error`() = runBlocking {
        // Given
        val expectedError = Throwable()

        every { repository.getAllGenres() } returns flow { throw expectedError }

        // When
        val result = useCase.executeGenres()

        // Then
       result.test {
           val error = expectError()
           assertThat(error, IsInstanceOf(Throwable::class.java))
           assertEquals(expectedError, error)
       }

    }

}