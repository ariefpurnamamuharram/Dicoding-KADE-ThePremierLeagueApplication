package id.ariefpurnamamuharram.myfootballapplication

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import id.ariefpurnamamuharram.myfootballapplication.matches.FavoritesMainFragment
import id.ariefpurnamamuharram.myfootballapplication.matches.MainMatchesFragment
import id.ariefpurnamamuharram.myfootballapplication.teams.MainTeamsFragment
import org.jetbrains.anko.find

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Setup BottomNavigationView
        val bottomNavigationView = find<BottomNavigationView>(R.id.bottom_nav)
        bottomNavigationView.setOnNavigationItemSelectedListener(mItemSelectedListener)
        bottomNavigationView.selectedItemId = R.id.nav_matches
    }

    private val mItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.nav_matches -> {
                val matchesFragment = MainMatchesFragment.newInstance()
                openFragment(matchesFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_teams -> {
                val mainTeamsFragment = MainTeamsFragment.newInstance()
                openFragment(mainTeamsFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_favorites -> {
                val favouritesFragment = FavoritesMainFragment.newInstance()
                openFragment(favouritesFragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
