package com.delarosa.common.datasources

import com.delarosa.data.datasource.LocalTeamDataSource
import com.delarosa.domain.Team


class FakeLocalTeamDataSource : LocalTeamDataSource {

    var team: List<Team> = emptyList()

    override suspend fun isEmpty(code: String) = team.isEmpty()
    override suspend fun saveTeams(teams: List<Team>) {
        this.team = teams
    }

    override suspend fun getTeams(code: String): List<Team> = team

    override suspend fun findById(code: String): Team = team.first { it.code == code }

}


