package id.ariefpurnamamuharram.myfootballapplication.presenter

import com.google.gson.Gson
import id.ariefpurnamamuharram.myfootballapplication.TestContextProvider
import id.ariefpurnamamuharram.myfootballapplication.api.ApiRepository
import id.ariefpurnamamuharram.myfootballapplication.api.TheSportDBApi
import id.ariefpurnamamuharram.myfootballapplication.model.PlayerDetails
import id.ariefpurnamamuharram.myfootballapplication.model.PlayerDetailsResponse
import id.ariefpurnamamuharram.myfootballapplication.playerdetails.PlayerDetailsView
import id.ariefpurnamamuharram.myfootballapplication.teamdetails.PlayerDetailsPresenter
import id.ariefpurnamamuharram.myfootballapplication.view.LoadingView
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class PlayerDetailsPresenterTest {
    @Mock
    private
    lateinit var view: LoadingView

    @Mock
    private
    lateinit var content: PlayerDetailsView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    private lateinit var presenter: PlayerDetailsPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = PlayerDetailsPresenter(view, content, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testGetPlayerDetails() {
        val details: MutableList<PlayerDetails> = mutableListOf()
        val response = PlayerDetailsResponse(details)
        val idPlayer = "34145937"

        `when`(gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getPlayerDetails(idPlayer)),
                PlayerDetailsResponse::class.java)).thenReturn(response)

        presenter.getPlayerDetails(idPlayer)

        Mockito.verify(view).showLoading()
        Mockito.verify(content).showPlayerDetails(details)
        Mockito.verify(view).hideLoading()
    }
}