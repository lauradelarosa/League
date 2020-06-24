package com.delarosa.teamdetail.di


import com.delarosa.common.di.CommonComponent
import com.delarosa.common.di.FeatureScope
import com.delarosa.teamdetail.teamdetail.TeamDetailFragment
import com.delarosa.teamdetail.teamdetail.TeamDetailViewModel
import dagger.Component
import dagger.Subcomponent
import javax.inject.Named

@FeatureScope
@Component(dependencies = [(CommonComponent::class)], modules = [(TeamDetailModule::class)])
interface TeamDetailComponent {
    fun inject(teamDetailFragment: TeamDetailFragment)
}