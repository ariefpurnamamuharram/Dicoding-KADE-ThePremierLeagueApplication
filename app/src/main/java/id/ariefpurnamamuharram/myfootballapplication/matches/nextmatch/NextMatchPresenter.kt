package id.ariefpurnamamuharram.myfootballapplication.matches.nextmatch

import com.google.gson.Gson
import id.ariefpurnamamuharram.myfootballapplication.api.ApiRepository
import id.ariefpurnamamuharram.myfootballapplication.api.TheSportDBApi
import id.ariefpurnamamuharram.myfootballapplication.model.NextMatchResponse
import id.ariefpurnamamuharram.myfootballapplication.util.CoroutineContextProvider
import id.ariefpurnamamuharram.myfootballapplication.view.LoadingView
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class NextMatchPresenter(private val view: LoadingView,
                         private val content: NextMatchView,
                         private val apiRepository: ApiRepository,
                         private val gson: Gson,
                         private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getNextMatchList(league: String?) {
        view.showLoading()

        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getNextMatches(league)),
                        NextMatchResponse::class.java)
            }

            content.showNextMatchList(data.await().nextMatch)
            view.hideLoading()

        }
    }

}