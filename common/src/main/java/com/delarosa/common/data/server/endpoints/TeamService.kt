package com.delarosa.common.data.server.endpoints

import com.delarosa.common.data.server.endpoints.response.TeamResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TeamService {
    @GET("lookup_all_teams.php/")
    suspend fun getTeams(@Query("id") userId: String): Response<TeamResponse>

}