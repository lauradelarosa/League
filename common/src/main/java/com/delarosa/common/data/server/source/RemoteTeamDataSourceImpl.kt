package com.delarosa.common.data.server.source

import com.delarosa.common.data.server.endpoints.TeamService
import com.delarosa.common.data.server.endpoints.response.TeamResponse
import com.delarosa.data.ResultData
import com.delarosa.data.datasource.RemoteTeamDataSource
import com.delarosa.domain.Team
import com.delarosa.common.data.callServices
import com.delarosa.common.data.mappers.toDomainLeague
import com.delarosa.common.data.safeApiCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteTeamDataSourceImpl(private val teamService: TeamService) :
    RemoteTeamDataSource {
    override suspend fun getTeams(id: String): ResultData<List<Team>> =
        withContext(Dispatchers.IO) {
            safeApiCall(
                call = {
                    renderData(
                        teamService.getTeams(id).callServices()
                    )
                },
                errorMessage = " something failed calling service '../team'"
            )
        }

    private fun renderData(resultData: ResultData<TeamResponse>): ResultData<List<Team>> =
        when (resultData) {
            is ResultData.Success -> ResultData.Success(resultData.data.teams.map { it.toDomainLeague() })
            is ResultData.Error -> resultData
        }

}