package com.delarosa.league.data.server.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class LeagueResponse(
    val leagues: List<League>
)

@Parcelize
data class League(
    val idLeague: String,
    val idSoccerXML: String,
    val idAPIfootball: String,
    val strSport: String,
    val strLeague: String,
    val strLeagueAlternate: String,
    val strDivision: String,
    val idCup: String,
    val strCurrentSeason: String,
    val intFormedYear: String,
    val dateFirstEvent: String,
    val strGender: String,
    val strCountry: String,
    val strWebsite: String,
    val strFacebook: String,
    val strTwitter: String,
    val strYoutube: String,
    val strRSS: String,
    val strDescriptionEN: String,
    val strDescriptionDE: String,
    val strDescriptionFR: String,
    val strDescriptionIT: String,
    val strDescriptionCN: String,
    val strDescriptionJP: String,
    val strDescriptionRU: String,
    val strDescriptionES: String,
    val strDescriptionPT: String,
    val strDescriptionSE: String,
    val strDescriptionNL: String,
    val strDescriptionHU: String,
    val strDescriptionNO: String,
    val strDescriptionPL: String,
    val strDescriptionIL: String,
    val strFanart1: String,
    val strFanart2: String,
    val strFanart3: String,
    val strFanart4: String,
    val strBanner: String,
    val strBadge: String,
    val strLogo: String,
    val strPoster: String,
    val strTrophy: String,
    val strNaming: String,
    val strComplete: String,
    val strLocke: String
) : Parcelable