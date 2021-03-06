package com.delarosa.team.team

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.delarosa.common.di.ComponentProvider
import com.delarosa.common.presentation.Navigation
import com.delarosa.common.utils.Target
import com.delarosa.common.utils.canNavigate
import com.delarosa.team.databinding.FragmentTeamBinding
import com.delarosa.team.di.DaggerTeamComponent
import com.delarosa.team.di.TeamModule
import kotlinx.android.synthetic.main.fragment_team.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class TeamFragment : Fragment() {

    private lateinit var adapter: TeamAdapter
    private lateinit var dataBindingView: FragmentTeamBinding
    private var navigation: Navigation? = null

    @Inject
    lateinit var viewModelTeam: TeamViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        initDependencyInjection()

        navigation = context as? Navigation
            ?: throw IllegalStateException("Context needs to implement Navigation: $context")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        dataBindingView = FragmentTeamBinding.inflate(inflater, container, false).apply {
            viewModel = viewModelTeam
        }
        return dataBindingView.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dataBindingView.lifecycleOwner = this.viewLifecycleOwner

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = TeamAdapter(viewModelTeam::onItemClicked)
        recycler_team?.adapter = adapter

        viewModelTeam.navigation.observe(this, Observer { event ->
            event.getContentIfNotHandled()?.let {
                GlobalScope.launch(Dispatchers.IO) {
                    canNavigate(Target.TeamDetail)?.let { deepLink ->
                        navigation?.navigate("$deepLink$it")
                    }
                }
            }
        })
    }

    override fun onDetach() {
        super.onDetach()
        navigation = null
    }


    private fun initDependencyInjection() =
        DaggerTeamComponent
            .builder()
            .commonComponent((activity!!.application as ComponentProvider).getCommonComponent())
            .teamModule(TeamModule(this))
            .build()
            .inject(this)

}