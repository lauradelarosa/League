package com.delarosa.team.team

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.delarosa.common.presentation.Event
import com.delarosa.common.presentation.ScopedViewModel
import com.delarosa.data.ResultData
import com.delarosa.domain.Team
import com.delarosa.usecases.GetTeams
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class TeamViewModel(
    val leagueCode: String,
    private val getTeams: GetTeams,
    override val uiDispatcher: CoroutineDispatcher
) : ScopedViewModel(uiDispatcher) {

    private val _navigation = MutableLiveData<Event<String>>()
    val navigation: LiveData<Event<String>> = _navigation

    val list: MutableLiveData<List<Team>> = MutableLiveData()
    val loadingProgressBar: MutableLiveData<Boolean> = MutableLiveData()

    init {
        initServiceCall()
    }

    private fun initServiceCall() {
        launch {
            loadingProgressBar.value = true
            when (val result = getTeams.invoke(leagueCode)) {
                is ResultData.Success -> {
                    list.value = result.data
                }
                is ResultData.Error -> {
                    result.exception.toString()
                }
            }
            loadingProgressBar.value = false
        }
    }

    fun onItemClicked(team: Team) {
        _navigation.value = Event(team.code)
    }


}