package com.delarosa.common

import android.app.Application
import com.delarosa.common.di.CommonComponent
import com.delarosa.common.di.ComponentProvider
import com.delarosa.common.di.DaggerCommonComponent
import com.delarosa.common.utils.isNull

class App : Application(), ComponentProvider {

    private var commonComponent: CommonComponent? = null

    override fun getCommonComponent(): CommonComponent {
        if (commonComponent.isNull) {
            commonComponent = DaggerCommonComponent
                .factory()
                .create(this)
        }

        return commonComponent ?: throw NullPointerException("Common component was not found")
    }
}