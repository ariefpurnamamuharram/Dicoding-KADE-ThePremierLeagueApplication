package id.ariefpurnamamuharram.myfootballapplication.matches

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.*
import id.ariefpurnamamuharram.myfootballapplication.R
import id.ariefpurnamamuharram.myfootballapplication.adapter.ViewPagerAdapter
import id.ariefpurnamamuharram.myfootballapplication.matches.lastmatch.LastMatchFragment
import id.ariefpurnamamuharram.myfootballapplication.matches.nextmatch.NextMatchFragment
import id.ariefpurnamamuharram.myfootballapplication.search.SearchEventsFragment
import org.jetbrains.anko.support.v4.find

class MainMatchesFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_matches, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)

        val viewPager: ViewPager = find(R.id.viewpager_matches)
        val viewTabs: TabLayout = find(R.id.tab_matches)
        val adapter = ViewPagerAdapter(childFragmentManager)

        adapter.addFragment(LastMatchFragment(), "Last 15 Matches")
        adapter.addFragment(NextMatchFragment(), "Next 15 Matches")

        viewPager.adapter = adapter
        viewTabs.setupWithViewPager(viewPager)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.search_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                true
            }

            R.id.search -> {
                val searchFragment = SearchEventsFragment()
                fragmentManager?.beginTransaction()?.replace(R.id.container, searchFragment,
                        SearchEventsFragment::class.java.simpleName)?.addToBackStack(null)?.commit()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {

        fun newInstance(): MainMatchesFragment = MainMatchesFragment()

    }

}