package com.delarosa.teamdetail.di


import com.delarosa.common.di.CommonComponent
import com.delarosa.common.di.FeatureScope
import com.delarosa.teamdetail.teamdetail.TeamDetailFragment
import dagger.Component

@FeatureScope
@Component(dependencies = [(CommonComponent::class)], modules = [(TeamDetailModule::class)])
interface TeamDetailComponent {
    fun inject(teamDetailFragment: TeamDetailFragment)
}