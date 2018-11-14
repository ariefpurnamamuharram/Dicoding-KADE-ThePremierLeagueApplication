package id.ariefpurnamamuharram.myfootballapplication.favorites.favoriteteam

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
import id.ariefpurnamamuharram.myfootballapplication.favorites.FavoriteTeamsAdapter
import id.ariefpurnamamuharram.myfootballapplication.model.FavoriteTeam
import id.ariefpurnamamuharram.myfootballapplication.teamdetails.TeamDetailsActivity
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity

class FavoriteTeamsFragment : Fragment(), FavoriteTeamsView {

    private var favoriteTeam: MutableList<FavoriteTeam> = mutableListOf()

    private lateinit var adapter: FavoriteTeamsAdapter
    private lateinit var listTeam: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val rootView = inflater.inflate(R.layout.fragment_favorite_teams, container, false)

        listTeam = rootView.find(R.id.favorite_teams)
        swipeRefresh = rootView.find(R.id.swipe_refresh)

        listTeam.layoutManager = LinearLayoutManager(ctx)
        adapter = FavoriteTeamsAdapter(this.context, favoriteTeam) {
            startActivity<TeamDetailsActivity>("idTeam" to it.idTeam)
        }
        listTeam.adapter = adapter

        showFavoritesList()

        swipeRefresh.onRefresh {
            favoriteTeam.clear()
            showFavoritesList()
        }

        return rootView

    }

    override fun showFavoritesList() {
        context?.database?.use {
            swipeRefresh.isRefreshing = false
            val result = select(FavoriteTeam.TABLE_FAVORITE_TEAM)
            val favorite = result.parseList(classParser<FavoriteTeam>())
            favoriteTeam.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }

    companion object {
        fun newInstance(): FavoriteTeamsFragment = FavoriteTeamsFragment()
    }
}