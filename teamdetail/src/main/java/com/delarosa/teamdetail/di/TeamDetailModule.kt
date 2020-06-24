package com.delarosa.teamdetail.di


import android.app.Application
import androidx.room.Room
import com.delarosa.common.BuildConfig
import com.delarosa.common.common.RetrofitBuild
import com.delarosa.data.datasource.LocalEventDataSource
import com.delarosa.data.datasource.RemoteEventDataSource
import com.delarosa.data.repository.EventRepository
import com.delarosa.data.repository.TeamRepository
import com.delarosa.teamdetail.data.database.PruebaDataBase
import com.delarosa.teamdetail.data.database.source.RoomEventDataSource
import com.delarosa.teamdetail.data.server.endpoints.EventService
import com.delarosa.teamdetail.data.server.source.RemoteEventDataSourceImpl
import com.delarosa.teamdetail.teamdetail.TeamDetailViewModel
import com.delarosa.usecases.GetEvents
import com.delarosa.usecases.GetTeam
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton


@Module
object TeamDetailModule {

    @Provides
    fun teamDetailViewModelProvider(
        teamCode: String,
        getTeam: GetTeam,
        getEvents: GetEvents,
        dispatcher: CoroutineDispatcher
    ) =
        TeamDetailViewModel(teamCode, getTeam, getEvents, dispatcher)

    @Provides
    fun getDispatcher(): CoroutineDispatcher = Dispatchers.Main

    @Provides
    @Singleton
    fun getTeamRepository(teamRepository: TeamRepository) =
        GetTeam(teamRepository)

    @Provides
    @Singleton
    fun getEventRepository(eventRepository: EventRepository) =
        GetEvents(eventRepository)

    @Provides
    @Singleton
    fun databaseProvider(app: Application) = Room.databaseBuilder(
        app,
        PruebaDataBase::class.java,
        "prueba-db"
    ).build()

    @Provides
    @Singleton
    fun retrofitEventProvider() :EventService = RetrofitBuild(baseUrl = BuildConfig.BASE_URL).retrofit.create(
        EventService::class.java)

    @Provides
    @Singleton
    fun localEVentDataSourceProvider(db: PruebaDataBase): LocalEventDataSource = RoomEventDataSource(db)

    @Provides
    @Singleton
    fun remoteEventDataSourceProvider(eventService: EventService): RemoteEventDataSource = RemoteEventDataSourceImpl(eventService)



}
