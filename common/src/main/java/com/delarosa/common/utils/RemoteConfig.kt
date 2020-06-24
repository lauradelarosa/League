package com.delarosa.common.utils

import androidx.fragment.app.Fragment
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

suspend fun Fragment.getDetail(): Boolean {
    return suspendCoroutine { continuation ->
        val remoteConfig = FirebaseRemoteConfig.getInstance()
        remoteConfig.fetch(0)
        val defaults = mapOf("detail_team" to true)
        remoteConfig.setDefaultsAsync(defaults)

        activity?.let {
            remoteConfig.fetchAndActivate().addOnCompleteListener(it) { task ->
                if (task.isSuccessful) {
                    val f = remoteConfig.getString("hola")
                    print(f)
                    val detail = remoteConfig.getBoolean("detail_team")
                    continuation.resume(detail)
                }

            }
        }


    }
}