package com.delarosa.usecases

import com.delarosa.data.ResultData
import com.delarosa.data.repository.EventRepository
import com.delarosa.testshared.mockedEvent
import com.delarosa.testshared.mockedTeam
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetEventsTest {

    private val eventRepository: EventRepository = mockk()

    private lateinit var getEvents: GetEvents

    @Before
    fun setUp() {
        getEvents = GetEvents(eventRepository)
    }

    @Test
    fun `invoke calls event repository`() {
        runBlocking {
            val events = listOf(mockedEvent.copy())
            val team = mockedTeam.copy(id = 1)
            coEvery { eventRepository.getEvents(team.code) } returns (ResultData.Success(events))
            when (val result = getEvents.invoke(team.code)) {
                is ResultData.Success -> {
                    Assert.assertEquals(events, result.data)
                }
            }
        }
    }
}