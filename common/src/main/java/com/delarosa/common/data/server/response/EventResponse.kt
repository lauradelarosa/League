package com.delarosa.common.data.server.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


data class EventResponse(
    val events: List<Event>
)

@Parcelize
data class Event(
    val idEvent: String,
    val idSoccerXML: String,
    val idAPIfootball: String,
    val strEvent: String,
    val strEventAlternate: String,
    val strFilename: String,
    val strSport: String,
    val idLeague: String,
    val strLeague: String,
    val strSeason: String,
    val strDescriptionEN: String,
    val strHomeTeam: String,
    val strAwayTeam: String,
    val intHomeScore: String,
    val intRound: String,
    val intAwayScore: String,
    val intSpectators: String,
    val strHomeGoalDetails: String,
    val strHomeRedCards: String,
    val strHomeYellowCards: String,
    val strHomeLineupGoalkeeper: String,
    val strHomeLineupDefense: String,
    val strHomeLineupMidfield: String,
    val strHomeLineupForward: String,
    val strHomeLineupSubstitutes: String,
    val strHomeFormation: String,
    val strAwayRedCards: String,
    val strAwayYellowCards: String,
    val strAwayGoalDetails: String,
    val strAwayLineupGoalkeeper: String,
    val strAwayLineupDefense: String,
    val strAwayLineupMidfield: String,
    val strAwayLineupForward: String,
    val strAwayLineupSubstitutes: String,
    val strAwayFormation: String,
    val intHomeShots: String,
    val intAwayShots: String,
    val dateEvent: String,
    val dateEventLocal: String,
    val strDate: String,
    val strTime: String,
    val strTimeLocal: String,
    val strTVStation: String,
    val idHomeTeam: String,
    val idAwayTeam: String,
    val strResult: String,
    val strCircuit: String,
    val strCountry: String,
    val strCity: String,
    val strPoster: String,
    val strFanart: String,
    val strThumb: String,
    val strBanner: String,
    val strMap: String,
    val strTweet1: String,
    val strTweet2: String,
    val strTweet3: String,
    val strVideo: String,
    val strPostponed: String,
    val strLocked: String
) : Parcelable