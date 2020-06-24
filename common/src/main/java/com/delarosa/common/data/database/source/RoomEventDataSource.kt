package com.delarosa.common.data.database.source


import com.delarosa.data.datasource.LocalEventDataSource
import com.delarosa.domain.Event
import com.delarosa.common.data.database.PruebaDataBase
import com.delarosa.common.data.mappers.toDomainEvent
import com.delarosa.common.data.mappers.toRoomEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RoomEventDataSource(db: PruebaDataBase) : LocalEventDataSource {

    private val eventDao = db.eventDao()

    override suspend fun isEmpty(code: String): Boolean =
        withContext(Dispatchers.IO) { eventDao.eventCount(code) <= 0 }

    override suspend fun saveEvents(events: List<Event>) {
        withContext(Dispatchers.IO) { eventDao.insertEvents(events.map { it.toRoomEvent() }) }
    }

    override suspend fun getEvents(): List<Event> =
        withContext(Dispatchers.IO) {
            eventDao.getAll().map { it.toDomainEvent() }.take(5)
        }

}