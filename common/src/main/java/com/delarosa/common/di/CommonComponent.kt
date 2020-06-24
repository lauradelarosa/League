package com.delarosa.common.di

import android.app.Application
import com.delarosa.data.repository.EventRepository
import com.delarosa.data.repository.LeagueRepository
import com.delarosa.data.repository.TeamRepository
import dagger.BindsInstance
import dagger.Component
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class])
interface CommonComponent {
    fun getDispatcher(): CoroutineDispatcher
    fun application(): Application

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): CommonComponent
    }
}