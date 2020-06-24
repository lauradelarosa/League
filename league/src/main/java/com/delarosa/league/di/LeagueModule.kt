package com.delarosa.league.di


import com.delarosa.common.utils.getViewModel
import com.delarosa.data.repository.LeagueRepository
import com.delarosa.league.league.LeagueFragment
import com.delarosa.league.league.LeagueViewModel
import com.delarosa.usecases.GetLeagues
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher



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
    fun getLeagueInteractor(leagueRepository: LeagueRepository) =
        GetLeagues(leagueRepository)



}
