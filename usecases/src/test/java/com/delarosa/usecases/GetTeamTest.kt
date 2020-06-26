package com.delarosa.usecases

import com.delarosa.data.repository.TeamRepository
import com.delarosa.testshared.mockedTeam
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetTeamTest {

    private val teamRepository: TeamRepository = mockk()

    lateinit var getTeam: GetTeam


    @Before
    fun setUp() {
        getTeam = GetTeam(teamRepository)
    }

    @Test
    fun `invoke calls team repository`() {
        runBlocking {
            val team = mockedTeam.copy()
            coEvery { teamRepository.findById(team.code) } returns (team)
            Assert.assertEquals(team, getTeam.invoke(team.code))
        }
    }
}