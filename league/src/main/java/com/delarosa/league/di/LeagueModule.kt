package com.delarosa.league.di


import android.app.Application
import androidx.room.Room
import com.delarosa.common.BuildConfig
import com.delarosa.common.common.RetrofitBuild
import com.delarosa.common.utils.getViewModel
import com.delarosa.data.datasource.LocalLeagueDataSource
import com.delarosa.data.datasource.RemoteLeagueDataSource
import com.delarosa.data.repository.LeagueRepository
import com.delarosa.league.data.database.PruebaDataBase
import com.delarosa.league.data.database.source.RoomLeagueDataSource
import com.delarosa.league.data.server.endpoints.LeagueService
import com.delarosa.league.data.server.source.RemoteLeagueDataSourceImpl
import com.delarosa.league.league.LeagueFragment
import com.delarosa.league.league.LeagueViewModel
import com.delarosa.usecases.GetLeagues
import dagger.Module
import dagger.Provides
import dagger.Reusable
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Named
import javax.inject.Singleton


@Module
class LeagueModule(private val fragment: LeagueFragment) {

    @Provides
    fun provideLeagueViewModel(
        getLeagues: GetLeagues, dispatcher: CoroutineDispatcher
    ): LeagueViewModel =
        fragment.getViewModel {
            LeagueViewModel(getLeagues, dispatcher)
        }

    @Provides
    fun getLeagueRepository(leagueRepository: LeagueRepository) =
        GetLeagues(leagueRepository)

    @Provides
    @Named("league-db")
    fun databaseProvider(app: Application) = Room.databaseBuilder(
        app,
        PruebaDataBase::class.java,
        "prueba-db"
    ).build()

    @Provides
    @Named("retrofit-league")
    fun retrofitLeagueProvider(): LeagueService =
        RetrofitBuild(baseUrl = BuildConfig.BASE_URL).retrofit.create(LeagueService::class.java)

    @Provides
    fun localLeagueDataSourceProvider(  @Named("league-db") db: PruebaDataBase): LocalLeagueDataSource =
        RoomLeagueDataSource(db)

    @Provides
    fun remoteLeagueDataSourceProvider( @Named("retrofit-league") leagueService: LeagueService): RemoteLeagueDataSource =
        RemoteLeagueDataSourceImpl(leagueService)

    @Provides
    fun leagueRepositoryProvider(
        remoteLeagueDataSource: RemoteLeagueDataSource,
        localLeagueDataSource: LocalLeagueDataSource
    ) = LeagueRepository(remoteLeagueDataSource, localLeagueDataSource)

}
