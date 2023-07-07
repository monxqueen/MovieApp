package com.monique.projetointegrador.features.moviedetails.domain.model

import com.monique.projetointegrador.domain.model.Genre
import com.monique.projetointegrador.features.moviedetails.domain.model.MovieDetail
import org.junit.Assert
import org.junit.Test

class MovieDetailTest {

    @Test
    fun `when getRating function is called, it should be returned as a 0-100 percentage`(){
        //given
        val genreList = mutableListOf<Genre>()
        val movieDetailed = MovieDetail(
            "",
            genres = genreList,
            1,
            "",
            "2001-09-13",
            null,
            8f,
            "Barbie",
        )
        val expectedResult = "80%"

        //when
        val result = movieDetailed.getRating()

        //then
        Assert.assertEquals(expectedResult, result)
    }

    @Test
    fun `when getReleaseYear function is called, it should only display the year that movie was released`(){
        //given
        val genreList = mutableListOf<Genre>()
        val movieDetailed = MovieDetail(
            "",
            genres = genreList,
            2,
            "",
            "2001-09-13",
            null,
            8f,
            "Lilo & Stitch",
        )
        val expectedResult = "2001"

        //when
        val result = movieDetailed.getReleaseYear()

        //then
        Assert.assertEquals(expectedResult, result)
    }

    @Test
    fun `when getRuntime function is called, it should be displayed with hours apart from minutes`(){
        //given
        val genreList = mutableListOf<Genre>()
        val movieDetailed = MovieDetail(
            "",
            genres = genreList,
            3,
            "",
            "2001-09-13",
            113,
            8f,
            "Moana",
        )
        val expectedResult = "1h 53min"

        //when
        val result = movieDetailed.getRuntime()

        //then
        Assert.assertEquals(expectedResult, result)
    }

    @Test
    fun `when getRuntime function is called and the movie duration is less than an hour, it should be displayed only in minutes`(){
        //given
        val genreList = mutableListOf<Genre>()
        val movieDetailed = MovieDetail(
            "",
            genres = genreList,
            3,
            "",
            "2001-09-13",
            45,
            8f,
            "Interstellar",
        )
        val expectedResult = "45min"

        //when
        val result = movieDetailed.getRuntime()

        //then
        Assert.assertEquals(expectedResult, result)
    }

    @Test
    fun `when getRuntime function is called and the runtime attribute is null, an empty string should be displayed`(){
        //given
        val genreList = mutableListOf<Genre>()
        val movieDetailed = MovieDetail(
            "",
            genres = genreList,
            3,
            "",
            "2001-09-13",
            null,
            8f,
            "Shrek",
        )
        val expectedResult = ""

        //when
        val result = movieDetailed.getRuntime()

        //then
        Assert.assertEquals(expectedResult, result)
    }
}