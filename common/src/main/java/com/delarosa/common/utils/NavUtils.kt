package com.delarosa.common.utils

import androidx.fragment.app.Fragment

suspend fun Fragment.canNavigate(target: Target): String? {
    var result: String? = null
    when (target) {
        Target.League -> result = "delarosa://league"
        Target.Team -> result = "delarosa://team/"
        Target.TeamDetail -> {
            val detail = getDetail()
            if (detail) result = "delarosa://teamdetail/"
        }
    }
    return result
}

enum class Target {
    League, Team, TeamDetail
}