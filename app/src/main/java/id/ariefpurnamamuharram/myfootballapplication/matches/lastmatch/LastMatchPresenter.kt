package id.ariefpurnamamuharram.myfootballapplication.matches.lastmatch

import com.google.gson.Gson
import id.ariefpurnamamuharram.myfootballapplication.api.ApiRepository
import id.ariefpurnamamuharram.myfootballapplication.api.TheSportDBApi
import id.ariefpurnamamuharram.myfootballapplication.model.LastMatchResponse
import id.ariefpurnamamuharram.myfootballapplication.util.CoroutineContextProvider
import id.ariefpurnamamuharram.myfootballapplication.view.LoadingView
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class LastMatchPresenter(private val view: LoadingView,
                         private val content: LastMatchView,
                         private val apiRepository: ApiRepository,
                         private val gson: Gson,
                         private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getLastMatchList(league: String?) {
        view.showLoading()

        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getLastMatches(league)),
                        LastMatchResponse::class.java)
            }

            content.showLastMatchList(data.await().lastMatch)
            view.hideLoading()

        }
    }

}