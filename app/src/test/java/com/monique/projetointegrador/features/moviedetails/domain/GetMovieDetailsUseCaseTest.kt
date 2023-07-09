package com.monique.projetointegrador.features.moviedetails.domain

import app.cash.turbine.test
import com.monique.projetointegrador.domain.model.Genre
import com.monique.projetointegrador.features.moviedetails.domain.model.Cast
import com.monique.projetointegrador.features.moviedetails.domain.model.Certification
import com.monique.projetointegrador.features.moviedetails.domain.model.MovieDetail
import com.monique.projetointegrador.features.moviedetails.domain.repository.MovieDetailsRepository
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
class GetMovieDetailsUseCaseTest {

    private val repository: MovieDetailsRepository = mockk()
    private val useCase = GetMovieDetailsUseCaseImpl(repository)

    @Test
    fun `executeMovie function should return the movie details when repository returns success`()
        = runBlocking {
        // Given
        val expectedResult = MovieDetail(
            backdropPath = null,
            genres = listOf(Genre(1, "romance")),
            id = 1,
            overview = "overview",
            releaseDate = "may 1st",
            runtime = 20000,
            voteAverage = 3.5f,
            title = "Barbie",
            isFavorite = true
        )

        every { repository.getMovieDetails(1) } returns flow { emit(expectedResult) }
        // When
        val result = useCase.executeMovie(1)

        // Then
        result.test {
            assertEquals(expectedResult, expectItem())
            expectComplete()
        }
    }

    @Test
    fun `executeMovie function should return a throwable when repository returns a throwable`()
            = runBlocking {
        // Given
        val expectedResult = Throwable()

        every { repository.getMovieDetails(1) } returns flow { throw expectedResult }
        // When
        val result = useCase.executeMovie(1)

        // Then
        result.test {
            val error = expectError()
            assertThat(error, IsInstanceOf(Throwable::class.java))
            assertEquals(expectedResult, error)
        }
    }

    @Test
    fun `executeCast function should return list of cast when repository returns success`()
            = runBlocking {
        // Given
        val expectedResult = listOf(
            Cast(name = "joana", profilePath = "profilePath",character = "barbie"),
            Cast(name = "james", profilePath = "profilePath",character = "ken")
        )

        every { repository.getCast(1) } returns flow { emit(expectedResult) }
        // When
        val result = useCase.executeCast(1)

        // Then
        result.test {
            assertEquals(expectedResult, expectItem())
            expectComplete()
        }
    }

    @Test
    fun `executeCast function should return a throwable when repository returns a throwable`()
            = runBlocking {
        // Given
        val expectedResult = Throwable()

        every { repository.getCast(1) } returns flow { throw expectedResult }
        // When
        val result = useCase.executeCast(1)

        // Then
        result.test {
            val error = expectError()
            assertThat(error, IsInstanceOf(Throwable::class.java))
            assertEquals(expectedResult, error)
        }
    }

    @Test
    fun `executeCertification function should return list of certifications when repository returns success`()
            = runBlocking {
        // Given
        val expectedResult = listOf(
            Certification(certification =  "L", type = 18),
        )

        every { repository.getCertification(1) } returns flow { emit(expectedResult) }
        // When
        val result = useCase.executeCertification(1)

        // Then
        result.test {
            assertEquals(expectedResult, expectItem())
            expectComplete()
        }
    }

    @Test
    fun `executeCertification function should return a throwable when repository returns a throwable`()
            = runBlocking {
        // Given
        val expectedResult = Throwable()

        every { repository.getCertification(1) } returns flow { throw expectedResult }
        // When
        val result = useCase.executeCertification(1)

        // Then
        result.test {
            val error = expectError()
            assertThat(error, IsInstanceOf(Throwable::class.java))
            assertEquals(expectedResult, error)
        }
    }

}