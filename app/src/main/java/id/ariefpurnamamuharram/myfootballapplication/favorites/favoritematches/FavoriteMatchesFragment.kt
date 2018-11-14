package id.ariefpurnamamuharram.myfootballapplication.favorites.favoritematches

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.ariefpurnamamuharram.myfootballapplication.R
import id.ariefpurnamamuharram.myfootballapplication.db.database
import id.ariefpurnamamuharram.myfootballapplication.matchdetails.MatchDetailsActivity
import id.ariefpurnamamuharram.myfootballapplication.model.FavoriteMatch
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity

class FavoriteMatchesFragment : Fragment(), FavoriteMatchesView {

    private var favoriteList: MutableList<FavoriteMatch> = mutableListOf()

    private lateinit var adapter: FavoriteMatchesAdapter
    private lateinit var favoriteMatches: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val rootView = inflater.inflate(R.layout.fragment_favorite_matches, container, false)


        favoriteMatches = rootView.find(R.id.favorite_matches)
        swipeRefresh = rootView.find(R.id.swipe_refresh)

        favoriteMatches.layoutManager = LinearLayoutManager(activity)
        adapter = FavoriteMatchesAdapter(this.context, favoriteList) {
            startActivity<MatchDetailsActivity>("idEvent" to it.idEvent,
                    "idHomeTeam" to it.idHomeTeam,
                    "idAwayTeam" to it.idAwayTeam)
        }
        favoriteMatches.adapter = adapter

        showFavoritesList()

        swipeRefresh.onRefresh {
            favoriteList.clear()
            showFavoritesList()
        }

        return rootView
    }

    override fun showFavoritesList() {
        context?.database?.use {
            swipeRefresh.isRefreshing = false
            val result = select(FavoriteMatch.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<FavoriteMatch>())
            favoriteList.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }

    companion object {
        fun newInstance(): FavoriteMatchesFragment = FavoriteMatchesFragment()
    }
}