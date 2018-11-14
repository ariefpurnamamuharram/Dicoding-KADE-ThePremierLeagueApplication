package id.ariefpurnamamuharram.myfootballapplication.presenter

import com.google.gson.Gson
import id.ariefpurnamamuharram.myfootballapplication.TestContextProvider
import id.ariefpurnamamuharram.myfootballapplication.api.ApiRepository
import id.ariefpurnamamuharram.myfootballapplication.api.TheSportDBApi
import id.ariefpurnamamuharram.myfootballapplication.matches.nextmatch.NextMatchPresenter
import id.ariefpurnamamuharram.myfootballapplication.model.NextMatch
import id.ariefpurnamamuharram.myfootballapplication.model.NextMatchResponse
import id.ariefpurnamamuharram.myfootballapplication.view.LoadingView
import id.ariefpurnamamuharram.myfootballapplication.matches.nextmatch.NextMatchView
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class NextMatchPresenterTest {
    @Mock
    private
    lateinit var view: LoadingView

    @Mock
    private
    lateinit var content: NextMatchView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    private lateinit var presenter: NextMatchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = NextMatchPresenter(view, content, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testGetNextMatchList() {
        val nextMatches: MutableList<NextMatch> = mutableListOf()
        val response = NextMatchResponse(nextMatches)
        val league = "4328"

        `when`(gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getNextMatches(league)),
                NextMatchResponse::class.java)).thenReturn(response)

        presenter.getNextMatchList(league)

        Mockito.verify(view).showLoading()
        Mockito.verify(content).showNextMatchList(nextMatches)
        Mockito.verify(view).hideLoading()
    }
}