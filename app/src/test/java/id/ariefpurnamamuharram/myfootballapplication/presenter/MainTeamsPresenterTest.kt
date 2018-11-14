package id.ariefpurnamamuharram.myfootballapplication.presenter

import com.google.gson.Gson
import id.ariefpurnamamuharram.myfootballapplication.TestContextProvider
import id.ariefpurnamamuharram.myfootballapplication.api.ApiRepository
import id.ariefpurnamamuharram.myfootballapplication.api.TheSportDBApi
import id.ariefpurnamamuharram.myfootballapplication.matches.lastmatch.MainTeamsPresenter
import id.ariefpurnamamuharram.myfootballapplication.model.ListAllTeams
import id.ariefpurnamamuharram.myfootballapplication.model.ListAllTeamsResponse
import id.ariefpurnamamuharram.myfootballapplication.teams.MainTeamsView
import id.ariefpurnamamuharram.myfootballapplication.view.LoadingView
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class MainTeamsPresenterTest {
    @Mock
    private
    lateinit var view: LoadingView

    @Mock
    private
    lateinit var content: MainTeamsView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    private lateinit var presenter: MainTeamsPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = MainTeamsPresenter(view, content, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testGetListTeams() {
        val teams: MutableList<ListAllTeams> = mutableListOf()
        val response = ListAllTeamsResponse(teams)
        val league = "4328"

        `when`(gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getListAllTeams(league)),
                ListAllTeamsResponse::class.java)).thenReturn(response)

        presenter.getListTeams(league)

        Mockito.verify(view).showLoading()
        Mockito.verify(content).showListTeams(teams)
        Mockito.verify(view).hideLoading()
    }
}