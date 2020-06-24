package com.delarosa.teamdetail.teamdetail


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.delarosa.common.di.ComponentProvider
import com.delarosa.common.utils.startLink
import com.delarosa.teamdetail.databinding.FragmentTeamDetailBinding
import com.delarosa.teamdetail.di.DaggerTeamDetailComponent
import com.delarosa.teamdetail.di.TeamDetailModule
import kotlinx.android.synthetic.main.fragment_team_detail.*
import javax.inject.Inject


class TeamDetailFragment : Fragment() {

    private lateinit var adapter: EventsAdapter
    @Inject
    lateinit var viewModelDetail: TeamDetailViewModel
    private lateinit var dataBindingView: FragmentTeamDetailBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        initDependencyInjection()

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        dataBindingView = FragmentTeamDetailBinding.inflate(inflater, container, false).apply {
            viewModel = viewModelDetail
        }
        return dataBindingView.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dataBindingView.lifecycleOwner = this.viewLifecycleOwner
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = EventsAdapter()
        recycler?.adapter = adapter
        viewModelDetail.model.observe(this, Observer(::updateUi))
    }

    private fun updateUi(link: String) {
        activity?.let { it.startLink(link) }
    }

    private fun initDependencyInjection() =
        DaggerTeamDetailComponent
            .builder()
            .commonComponent((activity!!.application as ComponentProvider).getCommonComponent())
            .teamDetailModule(TeamDetailModule(this))
            .build()
            .inject(this)


}