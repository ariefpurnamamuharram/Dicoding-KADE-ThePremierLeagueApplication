package id.ariefpurnamamuharram.myfootballapplication.teams

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Spinner
import com.google.gson.Gson
import id.ariefpurnamamuharram.myfootballapplication.R
import id.ariefpurnamamuharram.myfootballapplication.api.ApiRepository
import id.ariefpurnamamuharram.myfootballapplication.matches.lastmatch.MainTeamsPresenter
import id.ariefpurnamamuharram.myfootballapplication.model.ListAllTeams
import id.ariefpurnamamuharram.myfootballapplication.search.SearchTeamsFragment
import id.ariefpurnamamuharram.myfootballapplication.teamdetails.TeamDetailsActivity
import id.ariefpurnamamuharram.myfootballapplication.util.invisible
import id.ariefpurnamamuharram.myfootballapplication.util.visible
import id.ariefpurnamamuharram.myfootballapplication.view.LoadingView
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.startActivity

class MainTeamsFragment : Fragment(), LoadingView, MainTeamsView {

    private var teams: MutableList<ListAllTeams> = mutableListOf()

    private lateinit var presenter: MainTeamsPresenter
    private lateinit var listAllTeams: RecyclerView
    private lateinit var adapter: MainTeamsAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var spinnerLeague: Spinner
    private lateinit var currentLeague: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_teams, container, false)

        setHasOptionsMenu(true)

        val request = ApiRepository()
        val gson = Gson()

        listAllTeams = rootView.find(R.id.team_list)
        progressBar = rootView.find(R.id.progress_bar)
        spinnerLeague = rootView.find(R.id.spinner_teams)

        val spinnerItems = resources.getStringArray(R.array.league_list)
        val spinnerAdapter = ArrayAdapter(context, R.layout.simple_item_list_layout_2, spinnerItems)
        spinnerLeague.adapter = spinnerAdapter

        presenter = MainTeamsPresenter(this, this, request, gson)

        spinnerLeague.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                currentLeague = spinnerLeague.selectedItem.toString()
                when (currentLeague) {
                    "English Premier League" -> presenter.getListTeams("4328")
                    "German Bundesliga" -> presenter.getListTeams("4331")
                    "Spanish La Liga" -> presenter.getListTeams("4335")
                    "Italian Serie A" -> presenter.getListTeams("4332")
                    "French Ligue 1" -> presenter.getListTeams("4334")
                    else -> presenter.getListTeams("4328")
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        listAllTeams.layoutManager = LinearLayoutManager(activity)
        adapter = MainTeamsAdapter(this.context, teams) {
            startActivity<TeamDetailsActivity>("idTeam" to it.idTeam)
        }
        listAllTeams.adapter = adapter

        return rootView
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showListTeams(data: List<ListAllTeams>) {
        teams.clear()
        teams.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.search_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                true
            }

            R.id.search -> {
                val searchFragment = SearchTeamsFragment()
                fragmentManager?.beginTransaction()?.replace(R.id.container, searchFragment,
                        SearchTeamsFragment::class.java.simpleName)?.addToBackStack(null)?.commit()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        fun newInstance(): MainTeamsFragment = MainTeamsFragment()
    }

}