package com.delarosa.league.di


import com.delarosa.common.di.CommonComponent
import com.delarosa.common.di.FeatureScope
import com.delarosa.league.league.LeagueFragment
import dagger.Component

@FeatureScope
@Component(dependencies = [(CommonComponent::class)], modules = [(LeagueModule::class)])
interface LeagueComponent {
    fun inject(leagueFragment: LeagueFragment)
}