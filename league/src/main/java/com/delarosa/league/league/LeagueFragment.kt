package com.delarosa.league.league

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
import com.delarosa.league.databinding.FragmentLeagueBinding
import com.delarosa.league.di.LeagueComponent
import com.delarosa.league.di.LeagueModule
import kotlinx.android.synthetic.main.fragment_league.*

class LeagueFragment : Fragment() {

    private lateinit var adapter: LeagueAdapter
    private lateinit var dataBindingView: FragmentLeagueBinding
    private lateinit var component: LeagueComponent
    private val viewModelLeague by lazy { getViewModel { component.leagueViewModel } }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        dataBindingView = FragmentLeagueBinding.inflate(inflater, container, false).apply {
            viewModel = viewModelLeague
        }
        return dataBindingView.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        context?.let {
            //component = it.app.component.plusLeague(LeagueModule())
        }
        dataBindingView.lifecycleOwner = this.viewLifecycleOwner
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = LeagueAdapter(viewModelLeague::onItemClicked)
        recycler?.adapter = adapter
        viewModelLeague.navigation.observe(this, Observer { event ->
            event.getContentIfNotHandled()?.let {
                findNavController().navigateUriWithDefaultOptions(
                    Uri.parse("delarosa://team/${it}")
                )
            }
        })
    }


}

