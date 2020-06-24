package com.delarosa.common.utils

import androidx.fragment.app.Fragment
import com.google.firebase.remoteconfig.FirebaseRemoteConfig

fun Fragment.canNavigate(target: Target):String? {
    var result: String? = null

    when (target) {
        Target.League -> result = "delarosa://league"
        Target.Team -> result = "delarosa://team/"
        Target.TeamDetail -> {
            val remoteConfig = FirebaseRemoteConfig.getInstance()
            remoteConfig.activate()

            val defaults = mapOf("detail" to true)

            remoteConfig.setDefaultsAsync(defaults)
            val detail = remoteConfig.getBoolean("detail")


            remoteConfig.fetch().addOnSuccessListener {
                if (detail)
                    result = "delarosa://teamdetail/"
            }
        }
    }
    return  result
}

enum class Target {
    League, Team, TeamDetail
}