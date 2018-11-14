package id.ariefpurnamamuharram.myfootballapplication.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class ViewPagerAdapter (fm: FragmentManager?): FragmentPagerAdapter(fm) {

    val fmList = arrayListOf<Fragment>()
    val titleList = arrayListOf<String>()

    fun addFragment(fragment: Fragment, title: String) {
        fmList.add(fragment)
        titleList.add(title)
    }

    override fun getItem(position: Int) = fmList[position]

    override fun getCount() = fmList.size

    override fun getPageTitle(position: Int) = titleList[position]
}