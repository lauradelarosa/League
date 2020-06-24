package com.delarosa.common.data.database.source


import com.delarosa.data.datasource.LocalTeamDataSource
import com.delarosa.domain.Team
import com.delarosa.common.data.database.PruebaDataBase
import com.delarosa.common.data.mappers.toDomainTeam
import com.delarosa.common.data.mappers.toRoomTeam
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RoomTeamDataSource(db: PruebaDataBase) : LocalTeamDataSource {

    private val teamDao = db.teamDao()

    override suspend fun isEmpty(code: String): Boolean =
        withContext(Dispatchers.IO) { teamDao.teamCount(code) <= 0 }

    override suspend fun saveTeams(teams: List<Team>) {
        withContext(Dispatchers.IO) { teamDao.insertTeam(teams.map { it.toRoomTeam() }) }
    }

    override suspend fun getTeams(code: String): List<Team> =
        withContext(Dispatchers.IO) {
            teamDao.getAll(code).map { it.toDomainTeam() }
        }

    override suspend fun findById(code: String): Team = withContext(Dispatchers.IO) {
        teamDao.findById(code).toDomainTeam()
    }
}