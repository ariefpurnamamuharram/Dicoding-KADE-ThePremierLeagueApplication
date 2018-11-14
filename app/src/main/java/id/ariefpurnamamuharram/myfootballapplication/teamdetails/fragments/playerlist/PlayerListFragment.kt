package id.ariefpurnamamuharram.myfootballapplication.teamdetails.fragments.playerlist

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.google.gson.Gson
import id.ariefpurnamamuharram.myfootballapplication.R
import id.ariefpurnamamuharram.myfootballapplication.api.ApiRepository
import id.ariefpurnamamuharram.myfootballapplication.model.PlayerList
import id.ariefpurnamamuharram.myfootballapplication.playerdetails.PlayerDetailsActivity
import id.ariefpurnamamuharram.myfootballapplication.teamdetails.PlayerListPresenter
import id.ariefpurnamamuharram.myfootballapplication.teams.PlayerListAdapter
import id.ariefpurnamamuharram.myfootballapplication.util.invisible
import id.ariefpurnamamuharram.myfootballapplication.util.visible
import id.ariefpurnamamuharram.myfootballapplication.view.LoadingView
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.startActivity

class PlayerListFragment : Fragment(), LoadingView, PlayerListView {

    private var player: MutableList<PlayerList> = mutableListOf()

    private lateinit var progressBar: ProgressBar
    private lateinit var playerList: RecyclerView
    private lateinit var adapter: PlayerListAdapter
    private lateinit var presenter: PlayerListPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val rootView = inflater.inflate(R.layout.fragment_player_list, container, false)

        val idTeam = arguments?.getString("idTeam")

        playerList = rootView.find(R.id.player_list)
        progressBar = rootView.find(R.id.progress_bar)

        val gson = Gson()
        val request = ApiRepository()

        presenter = PlayerListPresenter(this, this, request, gson)

        idTeam?.let {
            presenter.getPlayerList(it)
        }

        playerList.layoutManager = LinearLayoutManager(ctx)
        adapter = PlayerListAdapter(this.context, player) {
            startActivity<PlayerDetailsActivity>("idPlayer" to it.idPlayer)
        }
        playerList.adapter = adapter

        return rootView
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showPlayerList(data: List<PlayerList>) {
        player.clear()
        player.addAll(data)
        adapter.notifyDataSetChanged()
    }

    companion object {
        fun newInstance(): PlayerListFragment = PlayerListFragment()
    }
}