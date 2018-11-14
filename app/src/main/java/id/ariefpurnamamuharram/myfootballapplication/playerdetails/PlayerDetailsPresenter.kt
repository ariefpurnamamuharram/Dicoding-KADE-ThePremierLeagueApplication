package id.ariefpurnamamuharram.myfootballapplication.teamdetails

import com.google.gson.Gson
import id.ariefpurnamamuharram.myfootballapplication.api.ApiRepository
import id.ariefpurnamamuharram.myfootballapplication.api.TheSportDBApi
import id.ariefpurnamamuharram.myfootballapplication.model.PlayerDetailsResponse
import id.ariefpurnamamuharram.myfootballapplication.playerdetails.PlayerDetailsView
import id.ariefpurnamamuharram.myfootballapplication.util.CoroutineContextProvider
import id.ariefpurnamamuharram.myfootballapplication.view.LoadingView
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class PlayerDetailsPresenter(private val view: LoadingView,
                             private val content: PlayerDetailsView,
                             private val apiRepository: ApiRepository,
                             private val gson: Gson,
                             private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getPlayerDetails(idPlayer: String?) {
        view.showLoading()

        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getPlayerDetails(idPlayer)),
                        PlayerDetailsResponse::class.java)
            }

            content.showPlayerDetails(data.await().playerDetails)
            view.hideLoading()
        }
    }
}