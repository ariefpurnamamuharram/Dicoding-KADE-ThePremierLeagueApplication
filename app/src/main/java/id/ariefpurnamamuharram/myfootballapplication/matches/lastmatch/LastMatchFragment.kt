package id.ariefpurnamamuharram.myfootballapplication.matches.lastmatch

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Spinner
import com.google.gson.Gson
import id.ariefpurnamamuharram.myfootballapplication.R
import id.ariefpurnamamuharram.myfootballapplication.api.ApiRepository
import id.ariefpurnamamuharram.myfootballapplication.matchdetails.MatchDetailsActivity
import id.ariefpurnamamuharram.myfootballapplication.model.LastMatch
import id.ariefpurnamamuharram.myfootballapplication.util.invisible
import id.ariefpurnamamuharram.myfootballapplication.util.visible
import id.ariefpurnamamuharram.myfootballapplication.view.LoadingView
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.startActivity

class LastMatchFragment : Fragment(), LoadingView, LastMatchView {

    private var matches: MutableList<LastMatch> = mutableListOf()

    private lateinit var presenter: LastMatchPresenter
    private lateinit var listMatch: RecyclerView
    private lateinit var adapter: LastMatchAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var spinnerMatches: Spinner
    private lateinit var currentLeague: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val rootView = inflater.inflate(R.layout.fragment_last_match, container, false)

        val request = ApiRepository()
        val gson = Gson()

        listMatch = rootView.find(R.id.last_match_list)
        progressBar = rootView.find(R.id.progress_bar)
        spinnerMatches = rootView.find(R.id.spinner_last_match)

        val spinnerItems = resources.getStringArray(R.array.league_list)
        val spinnerAdapter = ArrayAdapter(context, R.layout.simple_item_list_layout_2, spinnerItems)
        spinnerMatches.adapter = spinnerAdapter

        presenter = LastMatchPresenter(this, this, request, gson)

        spinnerMatches.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                currentLeague = spinnerMatches.selectedItem.toString()
                when (currentLeague) {
                    "English Premier League" -> presenter.getLastMatchList("4328")
                    "German Bundesliga" -> presenter.getLastMatchList("4331")
                    "Spanish La Liga" -> presenter.getLastMatchList("4335")
                    "Italian Serie A" -> presenter.getLastMatchList("4332")
                    "French Ligue 1" -> presenter.getLastMatchList("4334")
                    else -> presenter.getLastMatchList("4328")
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        listMatch.layoutManager = LinearLayoutManager(activity)
        adapter = LastMatchAdapter(this.context, matches) {
            startActivity<MatchDetailsActivity>("idEvent" to it.idEvent,
                    "idHomeTeam" to it.idHomeTeam, "idAwayTeam" to it.idAwayteam)
        }
        listMatch.adapter = adapter

        return rootView
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showLastMatchList(data: List<LastMatch>) {
        matches.clear()
        matches.addAll(data)
        adapter.notifyDataSetChanged()
    }

    companion object {
        fun newInstance(): LastMatchFragment = LastMatchFragment()
    }
}