package com.delarosa.team

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.delarosa.data.ResultData
import com.delarosa.team.team.TeamViewModel
import com.delarosa.testshared.mockedLeague
import com.delarosa.testshared.mockedTeam
import com.delarosa.usecases.GetTeams
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.After
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class TeamViewModelTest {

    @get:Rule
    val ruleForLivaData = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()


    private val getTeams: GetTeams = mockk()

    private lateinit var vm: TeamViewModel

    private val league = mockedLeague.copy()


    private fun setUp() {

        vm = TeamViewModel(league.code, getTeams, testDispatcher)
    }

    @After
    fun tearDown() {

        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `getTeams is called`() {

        val teams = listOf(mockedTeam.copy(id = 1))

        runBlocking {

            coEvery {
                getTeams.invoke(league.code)
            } returns (ResultData.Success(teams))

            setUp()
            vm.list.observeForever { }
            vm.loadingProgressBar.observeForever {}

            assertEquals(false, vm.loadingProgressBar.value)
            assertEquals(teams, vm.list.value)

        }
    }
}