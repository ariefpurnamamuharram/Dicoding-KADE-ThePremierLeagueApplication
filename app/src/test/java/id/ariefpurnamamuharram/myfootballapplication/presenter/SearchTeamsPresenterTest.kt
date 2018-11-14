package id.ariefpurnamamuharram.myfootballapplication.presenter

import com.google.gson.Gson
import id.ariefpurnamamuharram.myfootballapplication.TestContextProvider
import id.ariefpurnamamuharram.myfootballapplication.api.ApiRepository
import id.ariefpurnamamuharram.myfootballapplication.api.TheSportDBApi
import id.ariefpurnamamuharram.myfootballapplication.matches.lastmatch.SearchTeamsPresenter
import id.ariefpurnamamuharram.myfootballapplication.model.TeamByName
import id.ariefpurnamamuharram.myfootballapplication.model.TeamByNameResponse
import id.ariefpurnamamuharram.myfootballapplication.search.SearchTeamsView
import id.ariefpurnamamuharram.myfootballapplication.view.LoadingView
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class SearchTeamsPresenterTest {
    @Mock
    private
    lateinit var view: LoadingView

    @Mock
    private
    lateinit var content: SearchTeamsView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    private lateinit var presenter: SearchTeamsPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = SearchTeamsPresenter(view, content, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testGetSearchTeams() {
        val name: MutableList<TeamByName> = mutableListOf()
        val response = TeamByNameResponse(name)
        val nameTeam = "Arsenal"

        `when`(gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getTeamByName(nameTeam)),
                TeamByNameResponse::class.java)).thenReturn(response)

        presenter.getSearchTeams(nameTeam)

        Mockito.verify(view).showLoading()
        Mockito.verify(content).showSearchTeams(name)
        Mockito.verify(view).hideLoading()
    }
}