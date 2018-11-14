package id.ariefpurnamamuharram.myfootballapplication.teamdetails.fragments.teamdescs

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import com.google.gson.Gson
import id.ariefpurnamamuharram.myfootballapplication.R
import id.ariefpurnamamuharram.myfootballapplication.api.ApiRepository
import id.ariefpurnamamuharram.myfootballapplication.model.TeamDetails
import id.ariefpurnamamuharram.myfootballapplication.teamdetails.TeamDetailsPresenter
import id.ariefpurnamamuharram.myfootballapplication.teamdetails.TeamDetailsView
import id.ariefpurnamamuharram.myfootballapplication.util.invisible
import id.ariefpurnamamuharram.myfootballapplication.util.visible
import id.ariefpurnamamuharram.myfootballapplication.view.LoadingView
import org.jetbrains.anko.find

class TeamDescsFragment : Fragment(), LoadingView, TeamDetailsView {

    private lateinit var teamDescs: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var presenter: TeamDetailsPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val rootView = inflater.inflate(R.layout.fragment_team_descs, container, false)

        teamDescs = rootView.find(R.id.team_descs)
        progressBar = rootView.find(R.id.progress_bar)

        val idTeam = arguments?.getString("idTeam")

        val request = ApiRepository()
        val gson = Gson()

        presenter = TeamDetailsPresenter(this, this, request, gson)

        idTeam?.let {
            presenter.getTeamDetails(it)
        }

        return rootView
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showTeamDetails(data: List<TeamDetails>) {
        teamDescs.text = data[0].strDescriptionEN
    }

    companion object {
        fun newInstance(): TeamDescsFragment = TeamDescsFragment()
    }
}