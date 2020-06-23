package com.delarosa.team.team

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.delarosa.common.utils.app
import com.delarosa.common.utils.getViewModel
import com.delarosa.common.utils.navigateUriWithDefaultOptions
import com.delarosa.team.databinding.FragmentTeamBinding
import com.delarosa.team.di.TeamComponent
import com.delarosa.team.di.TeamModule
import com.delarosa.team.team.TeamViewModel.Companion.LEAGUE_CODE
import kotlinx.android.synthetic.main.fragment_team.*

class TeamFragment : Fragment() {

    private lateinit var adapter: TeamAdapter
    private lateinit var dataBindingView: FragmentTeamBinding

    private lateinit var component: TeamComponent
    private val viewModelTeam by lazy { getViewModel { component.teamViewModel } }


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
        activity?.let {
           // component = it.app.component.plusTeam(TeamModule(it.intent.getStringExtra(LEAGUE_CODE)))
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        adapter = TeamAdapter(viewModelTeam::onItemClicked)
        recycler?.adapter = adapter

        viewModelTeam.navigation.observe(this, Observer { event ->
            event.getContentIfNotHandled()?.let {
                findNavController().navigateUriWithDefaultOptions(
                    Uri.parse("delarosa://teamdetail/${it}")
                )
            }
        })
    }


}