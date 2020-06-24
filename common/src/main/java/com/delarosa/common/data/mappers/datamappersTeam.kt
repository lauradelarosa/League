package com.delarosa.common.data.mappers

import com.delarosa.domain.Team
import com.delarosa.common.data.database.entities.Team as RoomTeam
import com.delarosa.common.data.server.endpoints.response.Team as ServerTeam

fun ServerTeam.toDomainLeague(): Team = Team(
    id = 0,
    code = idTeam,
    idLeague = idLeague,
    name = strTeam,
    description = strDescriptionEN,
    facebook = strFacebook,
    foundationYear = intFormedYear,
    stadium = strStadium,
    instagram = strInstagram,
    teamBadge = strTeamBadge,
    teamJersey = strTeamJersey,
    twitter = strTwitter,
    website = strWebsite,
    youtube = strYoutube
)

fun Team.toRoomTeam(): RoomTeam = RoomTeam(
    id = id,
    code = code,
    name = name,
    idLeague = idLeague,
    description = description,
    facebook = facebook,
    foundationYear = foundationYear,
    instagram = instagram,
    stadium = stadium,
    teamBadge = teamBadge,
    teamJersey = teamJersey,
    twitter = twitter,
    website = website,
    youtube = youtube
)

fun RoomTeam.toDomainTeam(): Team = Team(
    code = code,
    name = name,
    idLeague = idLeague,
    description = description,
    facebook = facebook,
    foundationYear = foundationYear,
    instagram = instagram,
    teamBadge = teamBadge,
    stadium = stadium,
    teamJersey = teamJersey,
    twitter = twitter,
    website = website,
    youtube = youtube,
    id = id
)