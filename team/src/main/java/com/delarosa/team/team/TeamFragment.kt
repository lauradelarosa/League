package com.delarosa.team.team

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.delarosa.common.di.ComponentProvider
import com.delarosa.common.utils.Target
import com.delarosa.common.utils.canNavigate
import com.delarosa.common.utils.navigateUriWithDefaultOptions
import com.delarosa.team.databinding.FragmentTeamBinding
import com.delarosa.team.di.DaggerTeamComponent
import com.delarosa.team.di.TeamComponent
import com.delarosa.team.di.TeamModule
import kotlinx.android.synthetic.main.fragment_team.*
import javax.inject.Inject

class TeamFragment : Fragment() {

    private lateinit var adapter: TeamAdapter
    private lateinit var dataBindingView: FragmentTeamBinding

    @Inject
    lateinit var viewModelTeam: TeamViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        initDependencyInjection()
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
        recycler?.adapter = adapter

        viewModelTeam.navigation.observe(this, Observer { event ->
            event.getContentIfNotHandled()?.let {
                canNavigate(Target.TeamDetail)?.let { deepLink->
                    findNavController().navigateUriWithDefaultOptions(
                        Uri.parse("$deepLink$it")
                    )
                }

            }
        })
    }

    private fun initDependencyInjection() =
        DaggerTeamComponent
            .builder()
            .commonComponent((activity!!.application as ComponentProvider).getCommonComponent())
            .teamModule(TeamModule(this))
            .build()
            .inject(this)

}