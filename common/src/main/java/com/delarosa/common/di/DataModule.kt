package com.delarosa.common.di


import com.delarosa.common.data.database.PruebaDataBase
import com.delarosa.common.data.database.source.RoomEventDataSource
import com.delarosa.common.data.database.source.RoomLeagueDataSource
import com.delarosa.common.data.database.source.RoomTeamDataSource
import com.delarosa.common.data.server.endpoints.EventService
import com.delarosa.common.data.server.endpoints.LeagueService
import com.delarosa.common.data.server.endpoints.TeamService
import com.delarosa.common.data.server.source.RemoteEventDataSourceImpl
import com.delarosa.common.data.server.source.RemoteLeagueDataSourceImpl
import com.delarosa.common.data.server.source.RemoteTeamDataSourceImpl
import com.delarosa.data.datasource.*
import com.delarosa.data.repository.EventRepository
import com.delarosa.data.repository.LeagueRepository
import com.delarosa.data.repository.TeamRepository
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class DataModule {

    @Provides
    fun teamRepositoryProvider(
        remoteTeamDataSource: RemoteTeamDataSource,
        localTeamDataSource: LocalTeamDataSource
    ) = TeamRepository(remoteTeamDataSource, localTeamDataSource)

    @Provides
    fun eventRepositoryProvider(
        remoteEventDataSource: RemoteEventDataSource,
        localEventDataSource: LocalEventDataSource
    ) = EventRepository(remoteEventDataSource, localEventDataSource)



    @Provides
    fun leagueRepositoryProvider(
        remoteLeagueDataSource: RemoteLeagueDataSource,
        localLeagueDataSource: LocalLeagueDataSource
    ) = LeagueRepository(remoteLeagueDataSource, localLeagueDataSource)



    @Provides
    fun localTeamDataSourceProvider(db: PruebaDataBase): LocalTeamDataSource =
        RoomTeamDataSource(db)

    @Provides
    fun remoteTeamDataSourceProvider( @Named("retrofit-team") teamService: TeamService): RemoteTeamDataSource =
        RemoteTeamDataSourceImpl(teamService)

    @Provides
    fun localLeagueDataSourceProvider(  db: PruebaDataBase): LocalLeagueDataSource =
        RoomLeagueDataSource(db)

    @Provides
    fun remoteLeagueDataSourceProvider( @Named("retrofit-league") leagueService: LeagueService): RemoteLeagueDataSource =
        RemoteLeagueDataSourceImpl(leagueService)

    @Provides
    fun localEVentDataSourceProvider( db: PruebaDataBase): LocalEventDataSource =
        RoomEventDataSource(db)

    @Provides
    fun remoteEventDataSourceProvider(@Named("retrofit-event") eventService: EventService): RemoteEventDataSource =
        RemoteEventDataSourceImpl(eventService)

}