package id.ariefpurnamamuharram.myfootballapplication.teamdetails

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import id.ariefpurnamamuharram.myfootballapplication.R
import id.ariefpurnamamuharram.myfootballapplication.adapter.ViewPagerAdapter
import id.ariefpurnamamuharram.myfootballapplication.api.ApiRepository
import id.ariefpurnamamuharram.myfootballapplication.db.database
import id.ariefpurnamamuharram.myfootballapplication.model.FavoriteTeam
import id.ariefpurnamamuharram.myfootballapplication.model.TeamDetails
import id.ariefpurnamamuharram.myfootballapplication.teamdetails.fragments.playerlist.PlayerListFragment
import id.ariefpurnamamuharram.myfootballapplication.teamdetails.fragments.teamdescs.TeamDescsFragment
import id.ariefpurnamamuharram.myfootballapplication.util.invisible
import id.ariefpurnamamuharram.myfootballapplication.util.visible
import id.ariefpurnamamuharram.myfootballapplication.view.LoadingView
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.find

class TeamDetailsActivity : AppCompatActivity(), LoadingView, TeamDetailsView {

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    private lateinit var presenter: TeamDetailsPresenter
    private lateinit var progressBar: ProgressBar
    private lateinit var teamBadge: ImageView
    private lateinit var teamName: TextView
    private lateinit var teamFormedYear: TextView
    private lateinit var teamStadium: TextView

    private lateinit var idTeam: String
    private lateinit var urlTeamBadge: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_details)

        progressBar = find(R.id.progress_bar)
        teamBadge = find(R.id.team_badge)
        teamName = find(R.id.team_name)
        teamFormedYear = find(R.id.team_year)
        teamStadium = find(R.id.team_stadium)

        val viewPager: ViewPager = find(R.id.viewpager_team_details)
        val viewTabs: TabLayout = find(R.id.tab_team_details)

        val request = ApiRepository()
        val gson = Gson()

        idTeam = intent.getStringExtra("idTeam")

        favoriteState(idTeam)

        presenter = TeamDetailsPresenter(this, this, request, gson)
        presenter.getTeamDetails(idTeam)

        val adapter = ViewPagerAdapter(supportFragmentManager)

        val bind = Bundle()
        bind.putString("idTeam", idTeam)

        val teamDescsFragment = TeamDescsFragment()
        teamDescsFragment.arguments = bind

        val playerListFragment = PlayerListFragment()
        playerListFragment.arguments = bind

        adapter.addFragment(teamDescsFragment, "Overview")
        adapter.addFragment(playerListFragment, "Players")

        viewPager.adapter = adapter
        viewTabs.setupWithViewPager(viewPager)

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
            R.id.add_to_favorite -> {
                if (isFavorite)
                    removeFromFavorite(idTeam)
                else addToFavorites(idTeam)

                isFavorite = !isFavorite
                setFavorite()

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addToFavorites(idTeam: String) {
        try {
            database.use {
                insert(FavoriteTeam.TABLE_FAVORITE_TEAM,
                        FavoriteTeam.ID_TEAM to idTeam,
                        FavoriteTeam.STR_TEAM to teamName.text.toString(),
                        FavoriteTeam.STR_TEAM_BADGE to urlTeamBadge)
            }
        } catch (e: SQLiteConstraintException) {
        }
    }

    private fun removeFromFavorite(idTeam: String) {
        try {
            database.use {
                delete(FavoriteTeam.TABLE_FAVORITE_TEAM, "ID_TEAM = {idTeam}",
                        "idTeam" to idTeam)
            }
        } catch (e: SQLiteConstraintException) {
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
    }

    private fun favoriteState(idTeam: String) {
        database.use {
            val result = select(FavoriteTeam.TABLE_FAVORITE_TEAM).whereArgs("ID_TEAM = {idTeam}",
                    "idTeam" to idTeam)
            val favorite = result.parseList(classParser<FavoriteTeam>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showTeamDetails(data: List<TeamDetails>) {
        urlTeamBadge = data[0].strTeamBadge.toString()
        Picasso.get().load(urlTeamBadge).into(teamBadge)
        teamName.text = data[0].strTeam
        teamFormedYear.text = data[0].intFormedYear
        teamStadium.text = data[0].strStadium
    }
}