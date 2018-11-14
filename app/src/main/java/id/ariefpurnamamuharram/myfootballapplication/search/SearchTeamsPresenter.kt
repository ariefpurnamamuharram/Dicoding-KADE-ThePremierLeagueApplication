package id.ariefpurnamamuharram.myfootballapplication.matches.lastmatch

import com.google.gson.Gson
import id.ariefpurnamamuharram.myfootballapplication.api.ApiRepository
import id.ariefpurnamamuharram.myfootballapplication.api.TheSportDBApi
import id.ariefpurnamamuharram.myfootballapplication.model.TeamByNameResponse
import id.ariefpurnamamuharram.myfootballapplication.search.SearchTeamsView
import id.ariefpurnamamuharram.myfootballapplication.util.CoroutineContextProvider
import id.ariefpurnamamuharram.myfootballapplication.view.LoadingView
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class SearchTeamsPresenter(private val view: LoadingView,
                           private val content: SearchTeamsView,
                           private val apiRepository: ApiRepository,
                           private val gson: Gson,
                           private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getSearchTeams(name: String?) {
        view.showLoading()

        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getTeamByName(name)),
                        TeamByNameResponse::class.java)
            }

            content.showSearchTeams(data.await().listTeamByName)
            view.hideLoading()
        }
    }

}