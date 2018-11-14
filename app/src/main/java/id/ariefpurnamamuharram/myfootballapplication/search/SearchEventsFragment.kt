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
import id.ariefpurnamamuharram.myfootballapplication.matchdetails.MatchDetailsActivity
import id.ariefpurnamamuharram.myfootballapplication.matches.lastmatch.SearchEventsAdapter
import id.ariefpurnamamuharram.myfootballapplication.matches.lastmatch.SearchEventsPresenter
import id.ariefpurnamamuharram.myfootballapplication.model.SearchEvents
import id.ariefpurnamamuharram.myfootballapplication.util.invisible
import id.ariefpurnamamuharram.myfootballapplication.util.visible
import id.ariefpurnamamuharram.myfootballapplication.view.LoadingView
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.startActivity

class SearchEventsFragment : Fragment(), LoadingView, SearchEventsView {

    private var matches: MutableList<SearchEvents> = mutableListOf()
    private lateinit var presenter: SearchEventsPresenter
    private lateinit var listMatch: RecyclerView
    private lateinit var adapter: SearchEventsAdapter
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val rootView = inflater.inflate(R.layout.fragment_search_match, container, false)

        setHasOptionsMenu(true)

        val request = ApiRepository()
        val gson = Gson()

        listMatch = rootView.find(R.id.search_match_list)
        progressBar = rootView.find(R.id.progress_bar)

        presenter = SearchEventsPresenter(this, this, request, gson)

        listMatch.layoutManager = LinearLayoutManager(activity)
        adapter = SearchEventsAdapter(this.context, matches) {
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

    override fun showSearchEvents(data: List<SearchEvents>) {
        matches.clear()
        matches.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.search_menu, menu)
        val searchView = menu?.findItem(R.id.search)?.actionView as SearchView?
        searchView?.queryHint = "Search…"

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                presenter.getSearchEvents(query)
                return false
            }

            override fun onQueryTextChange(newTest: String?): Boolean {
                return false
            }
        })

        searchView?.setOnCloseListener { true }
    }

    companion object {
        fun newInstance(): SearchEventsFragment = SearchEventsFragment()
    }
}