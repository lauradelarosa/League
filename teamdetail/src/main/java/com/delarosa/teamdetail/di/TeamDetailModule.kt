package com.delarosa.teamdetail.di


import android.app.Application
import androidx.room.Room
import com.delarosa.common.BuildConfig
import com.delarosa.common.common.RetrofitBuild
import com.delarosa.common.utils.getViewModel
import com.delarosa.data.datasource.LocalEventDataSource
import com.delarosa.data.datasource.LocalTeamDataSource
import com.delarosa.data.datasource.RemoteEventDataSource
import com.delarosa.data.datasource.RemoteTeamDataSource
import com.delarosa.data.repository.EventRepository
import com.delarosa.data.repository.TeamRepository
import com.delarosa.teamdetail.data.database.PruebaDataBase
import com.delarosa.teamdetail.data.database.source.RoomEventDataSource
import com.delarosa.teamdetail.data.server.endpoints.EventService
import com.delarosa.teamdetail.data.server.source.RemoteEventDataSourceImpl
import com.delarosa.teamdetail.teamdetail.TeamDetailFragment
import com.delarosa.teamdetail.teamdetail.TeamDetailViewModel
import com.delarosa.usecases.GetEvents
import com.delarosa.usecases.GetTeam
import com.delarosa.usecases.GetTeams
import dagger.Module
import dagger.Provides
import dagger.Reusable
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Named
import javax.inject.Singleton

const val DETAIL_CODE = "detail_code"

@Module
class TeamDetailModule(private val fragment: TeamDetailFragment) {

    @Provides
    @Named(DETAIL_CODE)
    fun provideDetailCode(): String = fragment.arguments?.getString(DETAIL_CODE) ?: ""

    @Provides
    fun teamViewModelProvider(
        @Named(DETAIL_CODE)
        detailCode: String,
        getTeam: GetTeam,
        getEvents: GetEvents,
        coroutineDispatcher: CoroutineDispatcher
    ): TeamDetailViewModel =
        fragment.getViewModel {
            TeamDetailViewModel(detailCode, getTeam, getEvents, coroutineDispatcher)
        }


    @Provides
    fun getTeamRepository(teamRepository: TeamRepository) =
        GetTeam(teamRepository)

    @Provides
    fun getEventRepository(eventRepository: EventRepository) =
        GetEvents(eventRepository)

    @Provides
    @Named("teamdetail-db")
    fun databaseProvider(app: Application) = Room.databaseBuilder(
        app,
        PruebaDataBase::class.java,
        "prueba-db"
    ).build()

    @Provides
    @Named("retrofit-event")
    fun retrofitEventProvider(): EventService =
        RetrofitBuild(baseUrl = BuildConfig.BASE_URL).retrofit.create(
            EventService::class.java
        )

    @Provides
    fun localEVentDataSourceProvider(@Named("teamdetail-db") db: PruebaDataBase): LocalEventDataSource =
        RoomEventDataSource(db)

    @Provides
    fun remoteEventDataSourceProvider(@Named("retrofit-event") eventService: EventService): RemoteEventDataSource =
        RemoteEventDataSourceImpl(eventService)


    @Provides
    fun eventRepositoryProvider(
        remoteEventDataSource: RemoteEventDataSource,
        localEventDataSource: LocalEventDataSource
    ) = EventRepository(remoteEventDataSource, localEventDataSource)

    @Named("team-repository-event")
    @Provides
    fun teamRepositoryProvider(
        remoteTeamDataSource: RemoteTeamDataSource,
        localTeamDataSource: LocalTeamDataSource
    ) = TeamRepository(remoteTeamDataSource, localTeamDataSource)


}
