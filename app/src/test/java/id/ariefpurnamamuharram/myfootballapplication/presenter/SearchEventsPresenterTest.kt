package id.ariefpurnamamuharram.myfootballapplication.presenter

import com.google.gson.Gson
import id.ariefpurnamamuharram.myfootballapplication.TestContextProvider
import id.ariefpurnamamuharram.myfootballapplication.api.ApiRepository
import id.ariefpurnamamuharram.myfootballapplication.api.TheSportDBApi
import id.ariefpurnamamuharram.myfootballapplication.matches.lastmatch.SearchEventsPresenter
import id.ariefpurnamamuharram.myfootballapplication.model.SearchEvents
import id.ariefpurnamamuharram.myfootballapplication.model.SearchEventsResponse
import id.ariefpurnamamuharram.myfootballapplication.search.SearchEventsView
import id.ariefpurnamamuharram.myfootballapplication.view.LoadingView
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class SearchEventsPresenterTest {
    @Mock
    private
    lateinit var view: LoadingView

    @Mock
    private
    lateinit var content: SearchEventsView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    private lateinit var presenter: SearchEventsPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = SearchEventsPresenter(view, content, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testGetSearchEvents() {
        val events: MutableList<SearchEvents> = mutableListOf()
        val response = SearchEventsResponse(events)
        val nameEvent = "Arsenal"

        `when`(gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getEventByName(nameEvent)),
                SearchEventsResponse::class.java)).thenReturn(response)

        presenter.getSearchEvents(nameEvent)

        Mockito.verify(view).showLoading()
        Mockito.verify(content).showSearchEvents(events)
        Mockito.verify(view).hideLoading()
    }
}