package id.ariefpurnamamuharram.myfootballapplication.matches.lastmatch

import com.google.gson.Gson
import id.ariefpurnamamuharram.myfootballapplication.api.ApiRepository
import id.ariefpurnamamuharram.myfootballapplication.api.TheSportDBApi
import id.ariefpurnamamuharram.myfootballapplication.model.ListAllTeamsResponse
import id.ariefpurnamamuharram.myfootballapplication.teams.MainTeamsView
import id.ariefpurnamamuharram.myfootballapplication.util.CoroutineContextProvider
import id.ariefpurnamamuharram.myfootballapplication.view.LoadingView
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class MainTeamsPresenter(private val view: LoadingView,
                         private val content: MainTeamsView,
                         private val apiRepository: ApiRepository,
                         private val gson: Gson,
                         private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getListTeams(league: String?) {
        view.showLoading()

        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getListAllTeams(league)),
                        ListAllTeamsResponse::class.java)
            }

            content.showListTeams(data.await().listAllTeams)
            view.hideLoading()
        }
    }

}