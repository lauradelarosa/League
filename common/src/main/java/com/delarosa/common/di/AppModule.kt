package com.delarosa.common.di


import android.app.Application
import androidx.room.Room
import com.delarosa.common.BuildConfig
import com.delarosa.common.data.RetrofitBuild
import com.delarosa.common.data.database.PruebaDataBase
import com.delarosa.common.data.server.endpoints.EventService
import com.delarosa.common.data.server.endpoints.LeagueService
import com.delarosa.common.data.server.endpoints.TeamService
import dagger.Module
import dagger.Provides
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