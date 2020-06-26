package com.delarosa.data

import com.delarosa.data.datasource.LocalLeagueDataSource
import com.delarosa.data.datasource.RemoteLeagueDataSource
import com.delarosa.data.repository.LeagueRepository
import com.delarosa.testshared.mockedLeague
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class LeagueRepositoryTest {

    private val remoteLeagueDataSource: RemoteLeagueDataSource = mockk()

    private val localLeagueDataSource: LocalLeagueDataSource = mockk()

    lateinit var leagueRepository: LeagueRepository

    @Before
    fun setUp() {
        leagueRepository = LeagueRepository(
            remoteLeagueDataSource = remoteLeagueDataSource,
            localLeagueDataSource = localLeagueDataSource
        )
    }

    @Test
    fun `remote league calls remoteLeagueDataSource `() {
        runBlocking {
            val remoteLeagues = listOf(mockedLeague.copy())
            coEvery { localLeagueDataSource.isNotComplete() } returns (true)
            coEvery { remoteLeagueDataSource.getLeague("123") } returns (
                    ResultData.Success(
                        remoteLeagues
                    )
                    )
            coEvery { localLeagueDataSource.saveLeague(remoteLeagues) } returns (Unit)
            coEvery { localLeagueDataSource.getLeagues() } returns (remoteLeagues)
            when (val result = leagueRepository.getLeague("123")) {
                is ResultData.Success -> assertEquals(remoteLeagues, result.data)
            }
        }
    }

}