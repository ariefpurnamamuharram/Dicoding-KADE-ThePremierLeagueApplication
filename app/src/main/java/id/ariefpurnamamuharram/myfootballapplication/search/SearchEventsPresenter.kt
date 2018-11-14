package id.ariefpurnamamuharram.myfootballapplication.matches.lastmatch

import com.google.gson.Gson
import id.ariefpurnamamuharram.myfootballapplication.api.ApiRepository
import id.ariefpurnamamuharram.myfootballapplication.api.TheSportDBApi
import id.ariefpurnamamuharram.myfootballapplication.model.SearchEventsResponse
import id.ariefpurnamamuharram.myfootballapplication.search.SearchEventsView
import id.ariefpurnamamuharram.myfootballapplication.util.CoroutineContextProvider
import id.ariefpurnamamuharram.myfootballapplication.view.LoadingView
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class SearchEventsPresenter(private val view: LoadingView,
                            private val content: SearchEventsView,
                            private val apiRepository: ApiRepository,
                            private val gson: Gson,
                            private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getSearchEvents(name: String?) {
        view.showLoading()

        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getEventByName(name)),
                        SearchEventsResponse::class.java)
            }

            content.showSearchEvents(data.await().searchMatch)
            view.hideLoading()
        }
    }

}