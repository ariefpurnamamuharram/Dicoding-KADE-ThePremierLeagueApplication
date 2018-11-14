package id.ariefpurnamamuharram.myfootballapplication.matchdetails

import com.google.gson.Gson
import id.ariefpurnamamuharram.myfootballapplication.api.ApiRepository
import id.ariefpurnamamuharram.myfootballapplication.api.TheSportDBApi
import id.ariefpurnamamuharram.myfootballapplication.model.AwayTeamBadgeResponse
import id.ariefpurnamamuharram.myfootballapplication.model.EventDetailsResponse
import id.ariefpurnamamuharram.myfootballapplication.model.HomeTeamBadgeResponse
import id.ariefpurnamamuharram.myfootballapplication.util.CoroutineContextProvider
import id.ariefpurnamamuharram.myfootballapplication.view.LoadingView
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class MatchDetailsPresenter(private val view: LoadingView,
                            private val content: MatchDetailsView,
                            private val apiRepository: ApiRepository,
                            private val gson: Gson,
                            private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getMatchDetails(idEvent: String?) {
        view.showLoading()

        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getEventDetails(idEvent)),
                        EventDetailsResponse::class.java)
            }

            content.showMatchDetails(data.await().eventDetails)
            view.hideLoading()

        }
    }

    fun getHomeTeamBadge(idTeam: String?) {
        async(UI) {
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getTeamBadge(idTeam)),
                        HomeTeamBadgeResponse::class.java)
            }

            content.showHomeTeamBadge(data.await().homeTeamBadge)

        }
    }

    fun getAwayTeamBadge(idTeam: String?) {
        async(UI) {
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getTeamBadge(idTeam)),
                        AwayTeamBadgeResponse::class.java)
            }

            content.showAwayTeamBadge(data.await().awayTeamBadge)

        }
    }

}