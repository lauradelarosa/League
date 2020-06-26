package com.delarosa.league

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.delarosa.data.ResultData
import com.delarosa.league.league.LeagueViewModel
import com.delarosa.testshared.mockedLeague
import com.delarosa.usecases.GetLeagues
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
class LeagueViewModelTest {

    @get:Rule
    val ruleForLivaData = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()

    private val getLeagues: GetLeagues = mockk()

    private lateinit var vm: LeagueViewModel


    private fun setUp() {

        vm = LeagueViewModel( getLeagues, testDispatcher)
    }

    @After
    fun tearDown() {
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `getLeagues is called`() {

        val list = listOf(mockedLeague.copy(code = "555"))

        runBlocking {
            
            coEvery {
                getLeagues.invoke()
            } returns (ResultData.Success(list))

            setUp()
            vm.list.observeForever { }
            vm.loadingProgressBar.observeForever {}

            assertEquals(false, vm.loadingProgressBar.value)
            assertEquals(list, vm.list.value)

        }
    }
}