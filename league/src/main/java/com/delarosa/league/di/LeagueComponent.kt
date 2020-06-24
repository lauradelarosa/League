package com.delarosa.league.di


import com.delarosa.common.di.CommonComponent
import com.delarosa.common.di.FeatureScope
import com.delarosa.league.league.LeagueViewModel
import dagger.Component
import dagger.Subcomponent

@FeatureScope
@Component(dependencies = [(CommonComponent::class)], modules = [(LeagueModule::class)])
interface LeagueComponent {
    val leagueViewModel: LeagueViewModel
}