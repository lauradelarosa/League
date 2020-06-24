package com.delarosa.team.di


import com.delarosa.common.di.CommonComponent
import com.delarosa.common.di.FeatureScope
import com.delarosa.data.datasource.LocalLeagueDataSource
import com.delarosa.data.datasource.LocalTeamDataSource
import com.delarosa.data.datasource.RemoteLeagueDataSource
import com.delarosa.data.datasource.RemoteTeamDataSource
import com.delarosa.data.repository.LeagueRepository
import com.delarosa.data.repository.TeamRepository
import com.delarosa.team.data.server.endpoints.TeamService
import com.delarosa.team.team.TeamFragment
import com.delarosa.team.team.TeamViewModel
import dagger.Component
import dagger.Subcomponent
import javax.inject.Named

@FeatureScope
@Component(dependencies = [(CommonComponent::class)], modules = [(TeamModule::class)])
interface TeamComponent {
    fun inject(teamFragment: TeamFragment)
    @Named("team-repository")
    fun teamRepositoryProvider(): TeamRepository
    fun remoteTeamDataSourceProvider(): RemoteTeamDataSource
    fun localTeamDataSourceProvider(): LocalTeamDataSource
    @Named("retrofit-team")
    fun retrofitTeamProvider(): TeamService

}