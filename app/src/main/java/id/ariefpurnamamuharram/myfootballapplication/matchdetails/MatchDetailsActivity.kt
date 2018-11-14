package id.ariefpurnamamuharram.myfootballapplication.matchdetails

import android.annotation.SuppressLint
import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import id.ariefpurnamamuharram.myfootballapplication.R
import id.ariefpurnamamuharram.myfootballapplication.R.drawable.ic_add_to_favorites
import id.ariefpurnamamuharram.myfootballapplication.R.drawable.ic_added_to_favorites
import id.ariefpurnamamuharram.myfootballapplication.R.id.add_to_favorite
import id.ariefpurnamamuharram.myfootballapplication.api.ApiRepository
import id.ariefpurnamamuharram.myfootballapplication.db.database
import id.ariefpurnamamuharram.myfootballapplication.model.AwayTeamBadge
import id.ariefpurnamamuharram.myfootballapplication.model.EventDetails
import id.ariefpurnamamuharram.myfootballapplication.model.FavoriteMatch
import id.ariefpurnamamuharram.myfootballapplication.model.HomeTeamBadge
import id.ariefpurnamamuharram.myfootballapplication.util.invisible
import id.ariefpurnamamuharram.myfootballapplication.util.visible
import id.ariefpurnamamuharram.myfootballapplication.view.LoadingView
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.find
import java.text.SimpleDateFormat
import java.util.*


class MatchDetailsActivity : AppCompatActivity(), LoadingView, MatchDetailsView {

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    private lateinit var presenter: MatchDetailsPresenter
    private lateinit var progressBar: ProgressBar
    private lateinit var dateEvent: TextView
    private lateinit var dateTime: TextView
    private lateinit var homeTeam: TextView
    private lateinit var awayTeam: TextView
    private lateinit var homeTeamBadge: ImageView
    private lateinit var awayTeamBadge: ImageView
    private lateinit var homeScore: TextView
    private lateinit var awayScore: TextView
    private lateinit var homeGoalDetails: ListView
    private lateinit var awayGoalDetails: ListView
    private lateinit var homeGoalDetailsList: List<String>
    private lateinit var awayGoalDetailsList: List<String>
    private lateinit var homeShots: TextView
    private lateinit var awayShots: TextView
    private lateinit var homeGoalkeeper: TextView
    private lateinit var awayGoalkeeper: TextView
    private lateinit var homeLineupDefense: ListView
    private lateinit var awayLineupDefense: ListView
    private lateinit var homeLineupDefenseList: List<String>
    private lateinit var awayLineupDefenseList: List<String>
    private lateinit var homeLineupMidfield: ListView
    private lateinit var awayLineupMidfield: ListView
    private lateinit var homeLineupMidfieldList: List<String>
    private lateinit var awayLineupMidfieldList: List<String>
    private lateinit var homeLineupForward: ListView
    private lateinit var awayLineupForward: ListView
    private lateinit var homeLineupForwardList: List<String>
    private lateinit var awayLineupForwardList: List<String>
    private lateinit var homeLineupSubstitutes: ListView
    private lateinit var awayLineupSubstitutes: ListView
    private lateinit var homeLineupSubstitutesList: List<String>
    private lateinit var awayLineupSubstitutesList: List<String>

    private lateinit var setIdEvent: String
    private lateinit var setIdHomeTeam: String
    private lateinit var setIdAwayTeam: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_details)

        progressBar = find(R.id.progress_bar)
        dateEvent = find(R.id.match_date)
        dateTime = find(R.id.match_time)
        homeTeam = find(R.id.home_team_name)
        awayTeam = find(R.id.away_team_name)
        homeTeamBadge = find(R.id.home_team_badge)
        awayTeamBadge = find(R.id.away_team_badge)
        homeScore = find(R.id.home_score)
        awayScore = find(R.id.away_score)
        homeGoalDetails = find(R.id.home_goal_details)
        awayGoalDetails = find(R.id.away_goal_details)
        homeShots = find(R.id.home_shots)
        awayShots = find(R.id.away_shots)
        homeGoalkeeper = find(R.id.home_goal_keeper)
        awayGoalkeeper = find(R.id.away_goal_keeper)
        homeLineupDefense = find(R.id.home_defense)
        awayLineupDefense = find(R.id.away_defense)
        homeLineupMidfield = find(R.id.home_midfield)
        awayLineupMidfield = find(R.id.away_midfield)
        homeLineupForward = find(R.id.home_forward)
        awayLineupForward = find(R.id.away_forward)
        homeLineupSubstitutes = find(R.id.home_substitutes)
        awayLineupSubstitutes = find(R.id.away_substitutes)

        val request = ApiRepository()
        val gson = Gson()

        setIdEvent = intent.getStringExtra("idEvent")
        setIdHomeTeam = intent.getStringExtra("idHomeTeam")
        setIdAwayTeam = intent.getStringExtra("idAwayTeam")

        favoriteState(setIdEvent)

        presenter = MatchDetailsPresenter(this, this, request, gson)
        presenter.getMatchDetails(setIdEvent)
        presenter.getHomeTeamBadge(setIdHomeTeam)
        presenter.getAwayTeamBadge(setIdAwayTeam)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            add_to_favorite -> {
                if (isFavorite)
                    removeFromFavorite(setIdEvent)
                else addToFavorites(setIdEvent,
                        setIdHomeTeam,
                        setIdAwayTeam)

                isFavorite = !isFavorite
                setFavorite()

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    @SuppressLint("SimpleDateFormat")
    override fun showMatchDetails(data: List<EventDetails>) {

        // Convert timezone to Asia/Jakarta
        val oldFormatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        oldFormatter.timeZone = TimeZone.getTimeZone("UTC")
        val dateFormatter = SimpleDateFormat("dd/MM/yyyy")
        val timeFormatter = SimpleDateFormat("HH:mm")

        dateEvent.text = dateFormatter.format(oldFormatter.parse(data[0].dateEvent + " " + data[0].strTime))
        dateTime.text = timeFormatter.format(oldFormatter.parse(data[0].dateEvent + " " + data[0].strTime))
        homeTeam.text = data[0].strHomeTeam
        awayTeam.text = data[0].strAwayTeam

        // Is there any data for intHomeScore and intAwayScore?
        if (data[0].intHomeScore != null && data[0].intAwayScore != null) {
            homeScore.text = data[0].intHomeScore
            awayScore.text = data[0].intAwayScore
        } else {
            homeScore.text = "0"
            awayScore.text = "0"
        }

        // Is there any data for homeGoalDetails and awayGoalDetails?
        if (data[0].strHomeGoalDetails != null && data[0].strAwayGoalDetails != null) {
            homeGoalDetailsList = data[0].strHomeGoalDetails.toString()
                    .split(";").map { it.trim() }

            awayGoalDetailsList = data[0].strAwayGoalDetails.toString()
                    .split(";").map { it.trim() }
        } else {
            homeGoalDetailsList = emptyList()
            awayGoalDetailsList = emptyList()
        }

        val homeGoalDetailsAdapter = ArrayAdapter(this,
                R.layout.simple_item_list_layout, homeGoalDetailsList)

        val awayGoalDetailsAdapter = ArrayAdapter(this,
                R.layout.simple_item_list_layout, awayGoalDetailsList)

        homeGoalDetails.adapter = homeGoalDetailsAdapter
        awayGoalDetails.adapter = awayGoalDetailsAdapter

        homeShots.text = data[0].intHomeShots
        awayShots.text = data[0].intAwayShots

        homeGoalkeeper.text = data[0].strHomeLineupGoalkeeper
        awayGoalkeeper.text = data[0].strAwayLineupGoalkeeper

        // Is there any data for homeLineupDefense and awayLineupDefense?
        if (data[0].strHomeLineupDefense != null && data[0].strAwayLineupDefense != null) {
            homeLineupDefenseList = data[0].strHomeLineupDefense.toString()
                    .split(";").map { it.trim() }

            awayLineupDefenseList = data[0].strAwayLineupDefense.toString()
                    .split(";").map { it.trim() }
        } else {
            homeLineupDefenseList = emptyList()
            awayLineupDefenseList = emptyList()
        }

        val homeLineupDefenseAdapter = ArrayAdapter(this,
                R.layout.simple_item_list_layout, homeLineupDefenseList)

        val awayLineupDefenseAdapter = ArrayAdapter(this,
                R.layout.simple_item_list_layout, awayLineupDefenseList)

        homeLineupDefense.adapter = homeLineupDefenseAdapter
        awayLineupDefense.adapter = awayLineupDefenseAdapter

        // Is there any data for homeLineupMidfield and awayLineupMidfield?
        if (data[0].strHomeLineupMidfield != null && data[0].strAwayLineupMidfield != null) {
            homeLineupMidfieldList = data[0].strHomeLineupMidfield.toString()
                    .split(";").map { it.trim() }

            awayLineupMidfieldList = data[0].strAwayLineupMidfield.toString()
                    .split(";").map { it.trim() }
        } else {
            homeLineupMidfieldList = emptyList()
            awayLineupMidfieldList = emptyList()
        }

        val homeLineupMidfieldAdapter = ArrayAdapter(this,
                R.layout.simple_item_list_layout, homeLineupMidfieldList)

        val awayLineupMidfieldAdapter = ArrayAdapter(this,
                R.layout.simple_item_list_layout, awayLineupMidfieldList)

        homeLineupMidfield.adapter = homeLineupMidfieldAdapter
        awayLineupMidfield.adapter = awayLineupMidfieldAdapter

        // Is there any data for homeLineupForward and awayLineupForward?
        if (data[0].strHomeLineupForward != null && data[0].strAwayLineupForward != null) {
            homeLineupForwardList = data[0].strHomeLineupForward.toString()
                    .split(";").map { it.trim() }

            awayLineupForwardList = data[0].strAwayLineupForward.toString()
                    .split(";").map { it.trim() }
        } else {
            homeLineupForwardList = emptyList()
            awayLineupForwardList = emptyList()
        }

        val homeLineupForwardAdapter = ArrayAdapter(this,
                R.layout.simple_item_list_layout, homeLineupForwardList)

        val awayLineupForwardAdapter = ArrayAdapter(this,
                R.layout.simple_item_list_layout, awayLineupForwardList)

        homeLineupForward.adapter = homeLineupForwardAdapter
        awayLineupForward.adapter = awayLineupForwardAdapter

        // Is there any data for homeLineupSubstitutes and awayLineupSubstitutes?
        if (data[0].strHomeLineupSubstitutes != null && data[0].strAwayLineupSubstitutes != null) {
            homeLineupSubstitutesList = data[0].strHomeLineupSubstitutes.toString()
                    .split(";").map { it.trim() }

            awayLineupSubstitutesList = data[0].strAwayLineupSubstitutes.toString()
                    .split(";").map { it.trim() }
        } else {
            homeLineupSubstitutesList = emptyList()
            awayLineupSubstitutesList = emptyList()
        }

        val homeLineupSubstitutesAdapter = ArrayAdapter(this,
                R.layout.simple_item_list_layout, homeLineupSubstitutesList)

        val awayLineupSubstitutesAdapter = ArrayAdapter(this,
                R.layout.simple_item_list_layout, awayLineupSubstitutesList)

        homeLineupSubstitutes.adapter = homeLineupSubstitutesAdapter
        awayLineupSubstitutes.adapter = awayLineupSubstitutesAdapter

    }

    override fun showHomeTeamBadge(data: List<HomeTeamBadge>) {
        val url: String? = data[0].strTeamBadge.toString()
        Picasso.get().load(url).into(homeTeamBadge)
    }

    override fun showAwayTeamBadge(data: List<AwayTeamBadge>) {
        val url: String? = data[0].strTeamBadge.toString()
        Picasso.get().load(url).into(awayTeamBadge)
    }

    private fun addToFavorites(idEvent: String,
                               idHomeTeam: String,
                               idAwayTeam: String) {
        try {
            database.use {
                insert(FavoriteMatch.TABLE_FAVORITE,
                        FavoriteMatch.ID_EVENT to idEvent,
                        FavoriteMatch.ID_HOME_TEAM to idHomeTeam,
                        FavoriteMatch.ID_AWAY_TEAM to idAwayTeam,
                        FavoriteMatch.DATE_EVENT to dateEvent.text.toString(),
                        FavoriteMatch.STR_TIME to dateTime.text.toString(),
                        FavoriteMatch.STR_HOME_TEAM to homeTeam.text.toString(),
                        FavoriteMatch.STR_AWAY_TEAM to awayTeam.text.toString(),
                        FavoriteMatch.INT_HOME_SCORE to homeScore.text.toString(),
                        FavoriteMatch.INT_AWAY_SCORE to awayScore.text.toString())
            }
        } catch (e: SQLiteConstraintException) {
        }
    }

    private fun removeFromFavorite(idEvent: String) {
        try {
            database.use {
                delete(FavoriteMatch.TABLE_FAVORITE, "ID_EVENT = {idEvent}",
                        "idEvent" to idEvent)
            }
        } catch (e: SQLiteConstraintException) {
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_to_favorites)
    }

    private fun favoriteState(idEvent: String) {
        database.use {
            val result = select(FavoriteMatch.TABLE_FAVORITE).whereArgs("ID_EVENT = {idEvent}",
                    "idEvent" to idEvent)
            val favorite = result.parseList(classParser<FavoriteMatch>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }
}