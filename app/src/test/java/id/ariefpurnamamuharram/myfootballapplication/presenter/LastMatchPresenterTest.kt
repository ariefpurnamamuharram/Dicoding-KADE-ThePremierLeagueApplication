package id.ariefpurnamamuharram.myfootballapplication.presenter

import com.google.gson.Gson
import id.ariefpurnamamuharram.myfootballapplication.api.ApiRepository
import id.ariefpurnamamuharram.myfootballapplication.api.TheSportDBApi
import id.ariefpurnamamuharram.myfootballapplication.model.LastMatch
import id.ariefpurnamamuharram.myfootballapplication.model.LastMatchResponse
import id.ariefpurnamamuharram.myfootballapplication.matches.lastmatch.LastMatchView
import id.ariefpurnamamuharram.myfootballapplication.view.LoadingView
import id.ariefpurnamamuharram.myfootballapplication.TestContextProvider
import id.ariefpurnamamuharram.myfootballapplication.matches.lastmatch.LastMatchPresenter
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class LastMatchPresenterTest {
    @Mock
    private
    lateinit var view: LoadingView

    @Mock
    private
    lateinit var content: LastMatchView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    private lateinit var presenter: LastMatchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = LastMatchPresenter(view, content, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testGetLastMatchList() {
        val lastMatches: MutableList<LastMatch> = mutableListOf()
        val response = LastMatchResponse(lastMatches)
        val league = "4328"

        `when`(gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getLastMatches(league)),
                LastMatchResponse::class.java)).thenReturn(response)

        presenter.getLastMatchList(league)

        Mockito.verify(view).showLoading()
        Mockito.verify(content).showLastMatchList(lastMatches)
        Mockito.verify(view).hideLoading()
    }
}
