package id.ariefpurnamamuharram.myfootballapplication.presenter

import com.google.gson.Gson
import id.ariefpurnamamuharram.myfootballapplication.TestContextProvider
import id.ariefpurnamamuharram.myfootballapplication.api.ApiRepository
import id.ariefpurnamamuharram.myfootballapplication.api.TheSportDBApi
import id.ariefpurnamamuharram.myfootballapplication.matchdetails.MatchDetailsPresenter
import id.ariefpurnamamuharram.myfootballapplication.matchdetails.MatchDetailsView
import id.ariefpurnamamuharram.myfootballapplication.model.EventDetails
import id.ariefpurnamamuharram.myfootballapplication.model.EventDetailsResponse
import id.ariefpurnamamuharram.myfootballapplication.view.LoadingView
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class MatchDetailsPresenterTest {
    @Mock
    private
    lateinit var view: LoadingView

    @Mock
    private
    lateinit var content: MatchDetailsView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    private lateinit var presenter: MatchDetailsPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = MatchDetailsPresenter(view, content, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testGetMatchDetails() {
        val eventDetails: MutableList<EventDetails> = mutableListOf()
        val response = EventDetailsResponse(eventDetails)
        val event = "441613"

        `when`(gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getEventDetails(event)),
                EventDetailsResponse::class.java)).thenReturn(response)

        presenter.getMatchDetails(event)

        Mockito.verify(view).showLoading()
        Mockito.verify(content).showMatchDetails(eventDetails)
        Mockito.verify(view).hideLoading()
    }
}