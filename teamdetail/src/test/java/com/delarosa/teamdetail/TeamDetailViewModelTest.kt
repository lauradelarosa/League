package com.delarosa.teamdetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.delarosa.data.ResultData
import com.delarosa.teamdetail.teamdetail.TeamDetailViewModel
import com.delarosa.testshared.mockedEvent
import com.delarosa.testshared.mockedTeam
import com.delarosa.usecases.GetEvents
import com.delarosa.usecases.GetTeam
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
class TeamDetailViewModelTest {

    @get:Rule
    val ruleForLivaData = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()

    private val getTeam: GetTeam = mockk()
    private val getEvents: GetEvents = mockk()

    private lateinit var vm: TeamDetailViewModel
    private val team = mockedTeam.copy()

    private fun setUp() {
        vm = TeamDetailViewModel(team.code, getTeam, getEvents, testDispatcher)
    }

    @After
    fun tearDown() {
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `getEvents is called`() {

        val list = listOf(mockedEvent.copy(id = 22))

        runBlocking {

            coEvery {
                getTeam.invoke(team.code)
            } returns (team)

            coEvery {
                getEvents.invoke(team.code)
            } returns (ResultData.Success(list))

            setUp()
            vm.list.observeForever { }

            assertEquals(list, vm.list.value)

        }
    }
}