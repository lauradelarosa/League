package com.delarosa.team.di


import com.delarosa.common.di.CommonComponent
import com.delarosa.common.di.FeatureScope

import com.delarosa.team.team.TeamFragment
import com.delarosa.team.team.TeamViewModel
import dagger.Component
import dagger.Subcomponent
import javax.inject.Named

@FeatureScope
@Component(dependencies = [(CommonComponent::class)], modules = [(TeamModule::class)])
interface TeamComponent {
    fun inject(teamFragment: TeamFragment)

}