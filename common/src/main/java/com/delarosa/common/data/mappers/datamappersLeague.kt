package com.delarosa.common.data.mappers

import com.delarosa.domain.League
import com.delarosa.common.data.database.entities.League as RoomLeague
import com.delarosa.common.data.server.response.League as ServerLeague

fun ServerLeague.toDomainLeague(): League = League(
    code = idLeague,
    image = strLogo,
    name = strLeague,
    country = strCountry
)

fun League.toRoomLeague(): RoomLeague = RoomLeague(
    id = 0, image = image, country = country, code = code, title = name
)

fun RoomLeague.toDomainLeague() = League(
    code = code, name = title, country = country, image = image
)