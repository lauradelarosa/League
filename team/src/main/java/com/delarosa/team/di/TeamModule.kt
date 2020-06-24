package com.delarosa.team.di



import com.delarosa.common.utils.getViewModel
import com.delarosa.data.repository.TeamRepository
import com.delarosa.team.team.TeamFragment
import com.delarosa.team.team.TeamViewModel
import com.delarosa.usecases.GetTeams
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Named

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
    fun getTeamsInteractor(teamRepository: TeamRepository) =
        GetTeams(teamRepository)



}
