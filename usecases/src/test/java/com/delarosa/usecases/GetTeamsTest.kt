package com.delarosa.usecases

import com.delarosa.data.ResultData
import com.delarosa.data.repository.TeamRepository
import com.delarosa.testshared.mockedLeague
import com.delarosa.testshared.mockedTeam
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetTeamsTest {

    private val teamRepository: TeamRepository = mockk()

    private lateinit var getTeams: GetTeams


    @Before
    fun setUp() {
        getTeams = GetTeams(teamRepository)
    }

    @Test
    fun `invoke calls team repository`() {
        runBlocking {
            val team = listOf(mockedTeam.copy())
            val league = mockedLeague
            coEvery { teamRepository.getTeams(league.code) } returns (ResultData.Success(team))
            when (val result = getTeams.invoke(league.code)) {
                is ResultData.Success -> {
                    Assert.assertEquals(team, result.data)
                }
            }
        }
    }
}