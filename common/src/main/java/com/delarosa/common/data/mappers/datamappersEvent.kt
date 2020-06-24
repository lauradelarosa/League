package com.delarosa.common.data.mappers

import com.delarosa.domain.Event
import com.delarosa.common.data.database.entities.Event as RoomEvent
import com.delarosa.common.data.server.response.Event as ServerEvent

fun ServerEvent.toDomainEvent(idTeam: String): Event = Event(
    id = 0,
    idTeam = idTeam,
    homeText = strHomeTeam,
    awayText = strAwayTeam,
    date = dateEvent
)

fun Event.toRoomEvent(): RoomEvent = RoomEvent(
    id = id,
    homeText = homeText,
    awayText = awayText,
    date = date,
    idTeam = idTeam
)

fun RoomEvent.toDomainEvent(): Event = Event(
    id = id,
    homeText = homeText,
    awayText = awayText,
    date = date,
    idTeam = idTeam
)