package com.delarosa.data

import com.delarosa.data.datasource.LocalEventDataSource
import com.delarosa.data.datasource.RemoteEventDataSource
import com.delarosa.data.repository.EventRepository
import com.delarosa.testshared.mockedEvent
import com.delarosa.testshared.mockedTeam
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class EventRepositoryTest {

    private val remoteEventDataSource: RemoteEventDataSource = mockk()


    private val localEventDataSource: LocalEventDataSource = mockk()

    private lateinit var eventRepository: EventRepository

    @Before
    fun setUp() {
        eventRepository = EventRepository(remoteEventDataSource, localEventDataSource)
    }

    @Test
    fun `remote event calls remoteEventDataSource `() {
        runBlocking {
            val remoteEvents = listOf(mockedEvent.copy())
            val team = mockedTeam.copy(id = 1)

            coEvery { localEventDataSource.isEmpty(team.code) } returns (true)

            coEvery { remoteEventDataSource.getEvents(team.code) } returns (ResultData.Success(remoteEvents))

            coEvery { localEventDataSource.saveEvents(remoteEvents) } returns (Unit)

            coEvery { localEventDataSource.getEvents() } returns (remoteEvents)

            when (val result = eventRepository.getEvents(team.code)) {
                is ResultData.Success -> assertEquals(remoteEvents, result.data)
            }
        }
    }

}