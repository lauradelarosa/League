package com.delarosa.usecases

import com.delarosa.data.ResultData
import com.delarosa.data.repository.LeagueRepository
import com.delarosa.testshared.mockedLeague
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetLeaguesTest {

    private val leagueRepository: LeagueRepository = mockk()

    private lateinit var getLeagues: GetLeagues

    @Before
    fun setUp() {
        getLeagues = GetLeagues(leagueRepository)
    }

    @Test
    fun `invoke calls league repository`() {
        runBlocking {
            val league = listOf(mockedLeague.copy())

            coEvery {
                leagueRepository.getLeague(GetLeagues.League1)
                leagueRepository.getLeague(GetLeagues.League2)
                leagueRepository.getLeague(GetLeagues.League3)
            } returns (ResultData.Success(
                league
            ))

            when (val result = getLeagues.invoke()) {
                is ResultData.Success -> {
                    Assert.assertEquals(league, result.data)
                }
            }
        }
    }
}