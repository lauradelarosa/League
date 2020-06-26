package com.delarosa.data

import com.delarosa.data.datasource.LocalTeamDataSource
import com.delarosa.data.datasource.RemoteTeamDataSource
import com.delarosa.data.repository.TeamRepository
import com.delarosa.testshared.mockedLeague
import com.delarosa.testshared.mockedTeam
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class TeamRepositoryTest {

    private val remoteTeamDataSource: RemoteTeamDataSource = mockk()

    private val localTeamDataSource: LocalTeamDataSource = mockk()

    lateinit var teamRepository: TeamRepository

    @Before
    fun setUp() {
        teamRepository = TeamRepository(remoteTeamDataSource, localTeamDataSource)
    }

    @Test
    fun `remote team calls remoteTeamDataSource `() {
        runBlocking {
            val remoteTeam = listOf(mockedTeam.copy())
            val league = mockedLeague.copy()
            coEvery { localTeamDataSource.isEmpty(league.code) } returns (true)
            coEvery { remoteTeamDataSource.getTeams(league.code) } returns (ResultData.Success(remoteTeam))
            coEvery { localTeamDataSource.saveTeams(remoteTeam) } returns (Unit)
            coEvery { localTeamDataSource.getTeams(league.code) } returns (remoteTeam)
            when (val result = teamRepository.getTeams(league.code)) {
                is ResultData.Success -> assertEquals(remoteTeam, result.data)
            }
        }
    }

}