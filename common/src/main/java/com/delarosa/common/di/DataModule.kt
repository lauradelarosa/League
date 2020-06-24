package com.delarosa.common.di


import com.delarosa.data.datasource.*
import com.delarosa.data.repository.EventRepository
import com.delarosa.data.repository.LeagueRepository
import com.delarosa.data.repository.TeamRepository
import dagger.Module
import dagger.Provides
import dagger.Reusable
import javax.inject.Singleton

@Module
class DataModule {

    @Reusable
    @Provides
    fun teamRepositoryProvider(
        remoteTeamDataSource: RemoteTeamDataSource,
        localTeamDataSource: LocalTeamDataSource
    ) = TeamRepository(remoteTeamDataSource, localTeamDataSource)

    @Provides
    @Reusable
    fun leagueRepositoryProvider(
        remoteLeagueDataSource: RemoteLeagueDataSource,
        localLeagueDataSource: LocalLeagueDataSource
    ) = LeagueRepository(remoteLeagueDataSource, localLeagueDataSource)


    @Provides
    @Reusable
    fun eventRepositoryProvider(
        remoteEventDataSource: RemoteEventDataSource,
        localEventDataSource: LocalEventDataSource
    ) = EventRepository(remoteEventDataSource, localEventDataSource)

}