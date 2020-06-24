package com.delarosa.common.di

import android.app.Application
import androidx.room.RoomDatabase
import com.delarosa.common.data.server.endpoints.EventService
import com.delarosa.common.data.server.endpoints.LeagueService
import com.delarosa.common.data.server.endpoints.TeamService
import com.delarosa.data.datasource.*
import com.delarosa.data.repository.EventRepository
import com.delarosa.data.repository.LeagueRepository
import com.delarosa.data.repository.TeamRepository
import dagger.BindsInstance
import dagger.Component
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Named
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class, AppModule::class])
interface CommonComponent {

    fun getDispatcher(): CoroutineDispatcher
    fun application(): Application

    @Named("retrofit-league")
    fun retrofitLeague(): LeagueService

    @Named("retrofit-team")
    fun retrofitTeam(): TeamService

    @Named("retrofit-event")
    fun retrofitEvent(): EventService

    fun teamRepository(): TeamRepository
    fun eventRepository(): EventRepository
    fun leagueRepository(): LeagueRepository

    fun localTeamDataSource(): LocalTeamDataSource
    fun remoteTeamDataSource(): RemoteTeamDataSource
    fun localLeagueDataSource(): LocalLeagueDataSource
    fun remoteLeagueDataSource(): RemoteLeagueDataSource
    fun localEVentDataSource(): LocalEventDataSource
    fun remoteEventDataSource(): RemoteEventDataSource


    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): CommonComponent
    }
}