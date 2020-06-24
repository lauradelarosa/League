package com.delarosa.team.team


import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.delarosa.domain.Team

@BindingAdapter("items")
fun RecyclerView.setTeams(events: List<Team>?) {
    (adapter as? TeamAdapter)?.let {
        it.teamList = events ?: emptyList()
    }
}

