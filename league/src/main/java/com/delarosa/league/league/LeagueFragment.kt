package com.delarosa.league.league

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
import com.delarosa.league.databinding.FragmentLeagueBinding
import com.delarosa.league.di.DaggerLeagueComponent
import com.delarosa.league.di.LeagueModule
import kotlinx.android.synthetic.main.fragment_league.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class LeagueFragment : Fragment() {

    private lateinit var adapter: LeagueAdapter
    private lateinit var dataBindingView: FragmentLeagueBinding
    private var navigation: Navigation? = null

    @Inject
    lateinit var viewModelLeague: LeagueViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        initDependencyInjection()
        navigation = context as? Navigation ?: throw IllegalStateException("Context needs to implement Navigation: $context")

    }

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
        dataBindingView.lifecycleOwner = this.viewLifecycleOwner
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = LeagueAdapter(viewModelLeague::onItemClicked)
        recycler_league?.adapter = adapter
        viewModelLeague.navigation.observe(this, Observer { event ->
            event.getContentIfNotHandled()?.let {
                GlobalScope.launch(Dispatchers.IO) {
                canNavigate(Target.Team)?.let { deepLink ->
                    navigation?.navigate("$deepLink$it")
                }}
            }
        })
    }
    private fun initDependencyInjection() =
        DaggerLeagueComponent
            .builder()
            .commonComponent((activity!!.application as ComponentProvider).getCommonComponent())
            .leagueModule(LeagueModule(this))
            .build()
            .inject(this)


    override fun onDetach() {
        super.onDetach()
        navigation = null
    }

}

