package com.delarosa.teamdetail.di


import com.delarosa.common.di.CommonComponent
import com.delarosa.common.di.FeatureScope
import com.delarosa.data.datasource.LocalEventDataSource
import com.delarosa.data.datasource.LocalTeamDataSource
import com.delarosa.data.datasource.RemoteEventDataSource
import com.delarosa.data.datasource.RemoteTeamDataSource
import com.delarosa.data.repository.EventRepository
import com.delarosa.data.repository.TeamRepository
import com.delarosa.teamdetail.data.server.endpoints.EventService
import com.delarosa.teamdetail.teamdetail.TeamDetailFragment
import com.delarosa.teamdetail.teamdetail.TeamDetailViewModel
import dagger.Component
import dagger.Subcomponent
import javax.inject.Named

@FeatureScope
@Component(dependencies = [(CommonComponent::class)], modules = [(TeamDetailModule::class)])
interface TeamDetailComponent {
    fun inject(teamDetailFragment: TeamDetailFragment)

    @Named("team-repository-event")
    fun teamRepositoryProvider(): TeamRepository

    fun remoteEventDataSourceProvider(): RemoteEventDataSource
    fun localEventDataSourceProvider(): LocalEventDataSource
    fun eventRepositoryProvider(): EventRepository

    @Named("retrofit-event")
    fun retrofitEventProvider(): EventService
}