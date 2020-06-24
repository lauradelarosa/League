package com.delarosa.team.di


import android.app.Application
import androidx.room.Room
import com.delarosa.common.BuildConfig
import com.delarosa.common.common.RetrofitBuild
import com.delarosa.common.utils.getViewModel
import com.delarosa.data.datasource.LocalTeamDataSource
import com.delarosa.data.datasource.RemoteTeamDataSource
import com.delarosa.data.repository.TeamRepository
import com.delarosa.team.data.database.PruebaDataBase
import com.delarosa.team.data.database.source.RoomTeamDataSource
import com.delarosa.team.data.server.endpoints.TeamService
import com.delarosa.team.data.server.source.RemoteTeamDataSourceImpl
import com.delarosa.team.team.TeamFragment
import com.delarosa.team.team.TeamViewModel
import com.delarosa.usecases.GetTeams
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Named
import javax.inject.Singleton

const val LEAGUE_CODE = "league_code"

@Module
class TeamModule(private val fragment: TeamFragment) {

    @Provides
    @Named(LEAGUE_CODE)
    fun provideLeagueCode(): String = fragment.arguments?.getString(LEAGUE_CODE) ?: ""

    @Provides
    fun teamViewModelProvider(
        @Named(LEAGUE_CODE)
        leagueCode: String,
        getTeams: GetTeams,
        coroutineDispatcher: CoroutineDispatcher
    ): TeamViewModel =
        fragment.getViewModel {
            TeamViewModel(leagueCode, getTeams, coroutineDispatcher)
        }

    @Provides
    fun getDispatcher(): CoroutineDispatcher = Dispatchers.Main

    @Provides
    @Singleton
    fun getTeamsRepository(teamRepository: TeamRepository) =
        GetTeams(teamRepository)

    @Provides
    @Singleton
    fun databaseProvider(app: Application) = Room.databaseBuilder(
        app,
        PruebaDataBase::class.java,
        "prueba-db"
    ).build()

    @Provides
    @Singleton
    fun retrofitTeamProvider(): TeamService =
        RetrofitBuild(baseUrl = BuildConfig.BASE_URL).retrofit.create(TeamService::class.java)

    @Provides
    @Singleton
    fun localTeamDataSourceProvider(db: PruebaDataBase): LocalTeamDataSource =
        RoomTeamDataSource(db)

    @Provides
    @Singleton
    fun remoteTeamDataSourceProvider(teamService: TeamService): RemoteTeamDataSource =
        RemoteTeamDataSourceImpl(teamService)


}
