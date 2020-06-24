package com.delarosa.league.di


import com.delarosa.common.di.CommonComponent
import com.delarosa.common.di.FeatureScope
import com.delarosa.data.datasource.LocalLeagueDataSource
import com.delarosa.data.datasource.RemoteLeagueDataSource
import com.delarosa.data.repository.LeagueRepository
import com.delarosa.league.data.database.source.RoomLeagueDataSource
import com.delarosa.league.data.server.endpoints.LeagueService
import com.delarosa.league.data.server.source.RemoteLeagueDataSourceImpl
import com.delarosa.league.league.LeagueFragment
import dagger.Component
import javax.inject.Named

@FeatureScope
@Component(dependencies = [(CommonComponent::class)], modules = [(LeagueModule::class)])
interface LeagueComponent {
    fun inject(leagueFragment: LeagueFragment)

    fun leagueRepositoryProvider(): LeagueRepository
    fun remoteLeagueDataSourceProvider(): RemoteLeagueDataSource
    fun localLeagueDataSourceProvider(): LocalLeagueDataSource
    @Named("retrofit-league")
    fun retrofitLeagueProvider(): LeagueService
}