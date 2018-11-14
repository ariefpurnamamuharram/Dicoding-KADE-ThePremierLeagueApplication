package id.ariefpurnamamuharram.myfootballapplication.search

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.*
import android.widget.ProgressBar
import com.google.gson.Gson
import id.ariefpurnamamuharram.myfootballapplication.R
import id.ariefpurnamamuharram.myfootballapplication.api.ApiRepository
import id.ariefpurnamamuharram.myfootballapplication.matches.lastmatch.SearchTeamsPresenter
import id.ariefpurnamamuharram.myfootballapplication.model.TeamByName
import id.ariefpurnamamuharram.myfootballapplication.teamdetails.TeamDetailsActivity
import id.ariefpurnamamuharram.myfootballapplication.teams.SearchTeamsAdapter
import id.ariefpurnamamuharram.myfootballapplication.util.invisible
import id.ariefpurnamamuharram.myfootballapplication.util.visible
import id.ariefpurnamamuharram.myfootballapplication.view.LoadingView
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.startActivity

class SearchTeamsFragment : Fragment(), LoadingView, SearchTeamsView {

    private var teams: MutableList<TeamByName> = mutableListOf()

    private lateinit var presenter: SearchTeamsPresenter
    private lateinit var listTeamsByName: RecyclerView
    private lateinit var adapter: SearchTeamsAdapter
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_search_teams, container, false)

        setHasOptionsMenu(true)

        val request = ApiRepository()
        val gson = Gson()

        listTeamsByName = rootView.find(R.id.team_list)
        progressBar = rootView.find(R.id.progress_bar)

        presenter = SearchTeamsPresenter(this, this, request, gson)

        listTeamsByName.layoutManager = LinearLayoutManager(activity)
        adapter = SearchTeamsAdapter(this.context, teams) {
            startActivity<TeamDetailsActivity>("idTeam" to it.idTeam)
        }
        listTeamsByName.adapter = adapter

        return rootView
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showSearchTeams(data: List<TeamByName>) {
        teams.clear()
        teams.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.search_menu, menu)
        val searchView = menu?.findItem(R.id.search)?.actionView as SearchView?
        searchView?.queryHint = "Search…"

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                presenter.getSearchTeams(query)
                return false
            }

            override fun onQueryTextChange(newTest: String?): Boolean {
                return false
            }
        })

        searchView?.setOnCloseListener { true }
    }

    companion object {
        fun newInstance(): SearchTeamsFragment = SearchTeamsFragment()
    }
}