package id.ariefpurnamamuharram.myfootballapplication.matches

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.ariefpurnamamuharram.myfootballapplication.R
import id.ariefpurnamamuharram.myfootballapplication.adapter.ViewPagerAdapter
import id.ariefpurnamamuharram.myfootballapplication.favorites.favoritematches.FavoriteMatchesFragment
import id.ariefpurnamamuharram.myfootballapplication.favorites.favoriteteam.FavoriteTeamsFragment
import org.jetbrains.anko.support.v4.find

class FavoritesMainFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewPager: ViewPager = find(R.id.viewpager_favorites)
        val viewTabs: TabLayout = find(R.id.tab_favorites)
        val adapter = ViewPagerAdapter(childFragmentManager)

        adapter.addFragment(FavoriteMatchesFragment(), "Matches")
        adapter.addFragment(FavoriteTeamsFragment(), "Teams")

        viewPager.adapter = adapter
        viewTabs.setupWithViewPager(viewPager)
    }

    companion object {

        fun newInstance(): FavoritesMainFragment = FavoritesMainFragment()

    }

}