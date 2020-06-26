package com.delarosa.common.datasources

import com.delarosa.common.fakeTeamList
import com.delarosa.data.ResultData
import com.delarosa.data.datasource.RemoteTeamDataSource
import com.delarosa.domain.Team


class FakeRemoteTeamDataSource : RemoteTeamDataSource {

   private var remoteResponse = fakeTeamList

    override suspend fun getTeams(id: String): ResultData<List<Team>> =
        ResultData.Success(remoteResponse)

}


