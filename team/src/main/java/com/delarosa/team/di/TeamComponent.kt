package com.delarosa.team.di


import com.delarosa.common.di.CommonComponent
import com.delarosa.common.di.FeatureScope
import com.delarosa.team.team.TeamFragment
import dagger.Component

@FeatureScope
@Component(dependencies = [(CommonComponent::class)], modules = [(TeamModule::class)])
interface TeamComponent {
    fun inject(teamFragment: TeamFragment)

}