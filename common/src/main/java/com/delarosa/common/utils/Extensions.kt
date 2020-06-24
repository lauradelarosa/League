package com.delarosa.common.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.delarosa.common.App
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import kotlin.properties.Delegates


fun ImageView.loadUrl(url: String) {
    Glide.with(context).load(url).into(this)
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = true): View =
    LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)

inline fun <VH : RecyclerView.ViewHolder, T> RecyclerView.Adapter<VH>.basicDiffUtil(
    initialValue: List<T>,
    crossinline areItemsTheSame: (T, T) -> Boolean = { old, new -> old == new },
    crossinline areContentsTheSame: (T, T) -> Boolean = { old, new -> old == new }
) =
    Delegates.observable(initialValue) { _, old, new ->
        DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                areItemsTheSame(old[oldItemPosition], new[newItemPosition])

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                areContentsTheSame(old[oldItemPosition], new[newItemPosition])

            override fun getOldListSize(): Int = old.size

            override fun getNewListSize(): Int = new.size
        }).dispatchUpdatesTo(this@basicDiffUtil)
    }

fun Activity.startLink(link: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://$link"))
    intent.apply { addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) }
    startActivity(intent)
}

@Suppress("UNCHECKED_CAST")
inline fun <reified T : ViewModel> Fragment.getViewModel(crossinline factory: () -> T): T {

    val vmFactory = object : ViewModelProvider.Factory {
        override fun <U : ViewModel> create(modelClass: Class<U>): U = factory() as U
    }

    return ViewModelProvider(this, vmFactory).get()
}

val Context.app: App get() = applicationContext as App


val Any?.isNull: Boolean get() = this == null

enum class Target {
    League, Team, TeamDetail
}

fun canNavigate(target: Target): String? {
    var result: String? = null

    when (target) {
        Target.League -> result = "delarosa://league/"
        Target.Team -> result = "delarosa://team/"
        Target.TeamDetail -> {
            val defaults = mapOf("detail" to true)
            val remoteConfig = FirebaseRemoteConfig.getInstance()
            remoteConfig.setDefaultsAsync(defaults)
            val detail = remoteConfig.getBoolean("detail")

            remoteConfig.activate()
            remoteConfig.fetch().addOnSuccessListener {
                if (detail)
                    result = "delarosa://teamdetail/"
            }
        }
    }
    return result
}