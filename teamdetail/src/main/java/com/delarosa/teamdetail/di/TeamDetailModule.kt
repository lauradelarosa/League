package com.delarosa.teamdetail.di



import com.delarosa.common.utils.getViewModel
import com.delarosa.data.repository.EventRepository
import com.delarosa.data.repository.TeamRepository

import com.delarosa.teamdetail.teamdetail.TeamDetailFragment
import com.delarosa.teamdetail.teamdetail.TeamDetailViewModel
import com.delarosa.usecases.GetEvents
import com.delarosa.usecases.GetTeam
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Named

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
    fun getTeamInteractor(teamRepository: TeamRepository) =
        GetTeam(teamRepository)

    @Provides
    fun getEventInteractor(eventRepository: EventRepository) =
        GetEvents(eventRepository)



}
