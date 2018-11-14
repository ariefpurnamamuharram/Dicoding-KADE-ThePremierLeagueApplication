package id.ariefpurnamamuharram.myfootballapplication.presenter

import com.google.gson.Gson
import id.ariefpurnamamuharram.myfootballapplication.TestContextProvider
import id.ariefpurnamamuharram.myfootballapplication.api.ApiRepository
import id.ariefpurnamamuharram.myfootballapplication.api.TheSportDBApi
import id.ariefpurnamamuharram.myfootballapplication.matches.lastmatch.MainTeamsPresenter
import id.ariefpurnamamuharram.myfootballapplication.model.ListAllTeams
import id.ariefpurnamamuharram.myfootballapplication.model.ListAllTeamsResponse
import id.ariefpurnamamuharram.myfootballapplication.model.TeamDetails
import id.ariefpurnamamuharram.myfootballapplication.model.TeamDetailsResponse
import id.ariefpurnamamuharram.myfootballapplication.teamdetails.TeamDetailsPresenter
import id.ariefpurnamamuharram.myfootballapplication.teamdetails.TeamDetailsView
import id.ariefpurnamamuharram.myfootballapplication.teams.MainTeamsView
import id.ariefpurnamamuharram.myfootballapplication.view.LoadingView
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class TeamDetailsPresenterTest {
    @Mock
    private
    lateinit var view: LoadingView

    @Mock
    private
    lateinit var content: TeamDetailsView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    private lateinit var presenter: TeamDetailsPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = TeamDetailsPresenter(view, content, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testGetTeamDetails() {
        val teamDetails: MutableList<TeamDetails> = mutableListOf()
        val response = TeamDetailsResponse(teamDetails)
        val team = "133604"

        `when`(gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getTeamDetails(team)),
                TeamDetailsResponse::class.java)).thenReturn(response)

        presenter.getTeamDetails(team)

        Mockito.verify(view).showLoading()
        Mockito.verify(content).showTeamDetails(teamDetails)
        Mockito.verify(view).hideLoading()
    }
}