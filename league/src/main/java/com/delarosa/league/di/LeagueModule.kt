package com.delarosa.league.di


import android.app.Application
import androidx.room.Room
import com.delarosa.common.BuildConfig
import com.delarosa.common.common.RetrofitBuild
import com.delarosa.data.datasource.LocalLeagueDataSource
import com.delarosa.data.datasource.RemoteLeagueDataSource
import com.delarosa.data.repository.LeagueRepository
import com.delarosa.league.data.database.PruebaDataBase
import com.delarosa.league.data.database.source.RoomLeagueDataSource
import com.delarosa.league.data.server.endpoints.LeagueService
import com.delarosa.league.data.server.source.RemoteLeagueDataSourceImpl
import com.delarosa.league.league.LeagueViewModel
import com.delarosa.usecases.GetLeagues
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton


@Module
object LeagueModule {

    @Provides
    fun leagueViewModelProvider(getLeagues: GetLeagues, dispatcher: CoroutineDispatcher) =
        LeagueViewModel(getLeagues, dispatcher)

    @Provides
    fun getDispatcher(): CoroutineDispatcher = Dispatchers.Main

    @Provides
    @Singleton
    fun getLeagueRepository(leagueRepository: LeagueRepository) =
        GetLeagues(leagueRepository)

    @Provides
    @Singleton
    fun databaseProvider(app: Application) = Room.databaseBuilder(
        app,
        PruebaDataBase::class.java,
        "prueba-db"
    ).build()

    @Provides
    @Singleton
    fun retrofitLeagueProvider():LeagueService = RetrofitBuild(baseUrl = BuildConfig.BASE_URL).retrofit.create(LeagueService::class.java)

    @Provides
    @Singleton
    fun localLeagueDataSourceProvider(db: PruebaDataBase): LocalLeagueDataSource = RoomLeagueDataSource(db)

    @Provides
    @Singleton
    fun remoteLeagueDataSourceProvider(leagueService: LeagueService): RemoteLeagueDataSource = RemoteLeagueDataSourceImpl(leagueService)


}
