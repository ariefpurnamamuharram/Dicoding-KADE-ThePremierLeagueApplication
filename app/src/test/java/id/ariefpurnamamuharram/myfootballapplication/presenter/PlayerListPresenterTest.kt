package id.ariefpurnamamuharram.myfootballapplication.presenter

import com.google.gson.Gson
import id.ariefpurnamamuharram.myfootballapplication.TestContextProvider
import id.ariefpurnamamuharram.myfootballapplication.api.ApiRepository
import id.ariefpurnamamuharram.myfootballapplication.api.TheSportDBApi
import id.ariefpurnamamuharram.myfootballapplication.model.PlayerList
import id.ariefpurnamamuharram.myfootballapplication.model.PlayerListResponse
import id.ariefpurnamamuharram.myfootballapplication.teamdetails.PlayerListPresenter
import id.ariefpurnamamuharram.myfootballapplication.teamdetails.fragments.playerlist.PlayerListView
import id.ariefpurnamamuharram.myfootballapplication.view.LoadingView
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class PlayerListPresenterTest {
    @Mock
    private
    lateinit var view: LoadingView

    @Mock
    private
    lateinit var content: PlayerListView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    private lateinit var presenter: PlayerListPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = PlayerListPresenter(view, content, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testGetPlayerList() {
        val playerList: MutableList<PlayerList> = mutableListOf()
        val response = PlayerListResponse(playerList)
        val team = "133604"

        `when`(gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getPlayerList(team)),
                PlayerListResponse::class.java)).thenReturn(response)

        presenter.getPlayerList(team)

        Mockito.verify(view).showLoading()
        Mockito.verify(content).showPlayerList(playerList)
        Mockito.verify(view).hideLoading()
    }
}