package com.delarosa.common.di


import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.delarosa.common.BuildConfig
import com.delarosa.common.common.RetrofitBuild
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
import dagger.Reusable
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Named
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    fun getDispatcher(): CoroutineDispatcher = Dispatchers.Main

    @Provides
    @Singleton
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
    @Named("retrofit-team")
    fun retrofitTeamProvider(): TeamService =
        RetrofitBuild(baseUrl = BuildConfig.BASE_URL).retrofit.create(TeamService::class.java)


    @Provides
    @Named("retrofit-event")
    fun retrofitEventProvider(): EventService =
        RetrofitBuild(baseUrl = BuildConfig.BASE_URL).retrofit.create(
            EventService::class.java
        )


}