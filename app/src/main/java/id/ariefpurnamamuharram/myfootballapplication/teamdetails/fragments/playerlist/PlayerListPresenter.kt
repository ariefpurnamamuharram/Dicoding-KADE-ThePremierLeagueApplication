package id.ariefpurnamamuharram.myfootballapplication.teamdetails

import com.google.gson.Gson
import id.ariefpurnamamuharram.myfootballapplication.api.ApiRepository
import id.ariefpurnamamuharram.myfootballapplication.api.TheSportDBApi
import id.ariefpurnamamuharram.myfootballapplication.model.PlayerListResponse
import id.ariefpurnamamuharram.myfootballapplication.teamdetails.fragments.playerlist.PlayerListView
import id.ariefpurnamamuharram.myfootballapplication.util.CoroutineContextProvider
import id.ariefpurnamamuharram.myfootballapplication.view.LoadingView
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class PlayerListPresenter(private val view: LoadingView,
                          private val content: PlayerListView,
                          private val apiRepository: ApiRepository,
                          private val gson: Gson,
                          private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getPlayerList(team: String?) {
        view.showLoading()

        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getPlayerList(team)),
                        PlayerListResponse::class.java)
            }

            content.showPlayerList(data.await().playerList)
            view.hideLoading()
        }
    }
}