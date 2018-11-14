package id.ariefpurnamamuharram.myfootballapplication.matches.lastmatch

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import id.ariefpurnamamuharram.myfootballapplication.R
import id.ariefpurnamamuharram.myfootballapplication.model.SearchEvents
import org.jetbrains.anko.find

class SearchEventsAdapter(private val context: Context?,
                          private val searchMatch: List<SearchEvents>,
                          private val listener: (SearchEvents) -> Unit)
    : RecyclerView.Adapter<SearchMatchViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int) = SearchMatchViewHolder(LayoutInflater.from(p0.context)
            .inflate(R.layout.item_search_match, p0, false))

    override fun getItemCount(): Int = searchMatch.size

    override fun onBindViewHolder(p0: SearchMatchViewHolder, p1: Int) {
        p0.bindItem(searchMatch[p1], listener)
    }

}

class SearchMatchViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val eventName = view.find<TextView>(R.id.event_name)
    private val homeScore = view.find<TextView>(R.id.home_score)
    private val awayScore = view.find<TextView>(R.id.away_score)
    private val dateEvent = view.find<TextView>(R.id.match_date)
    private val timeEvent = view.find<TextView>(R.id.match_time)

    fun bindItem(searchMatch: SearchEvents, listener: (SearchEvents) -> Unit) {
        eventName.text = searchMatch.strEvent
        homeScore.text = searchMatch.intHomeScore
        awayScore.text = searchMatch.intAwayScore
        dateEvent.text = searchMatch.dateEvent
        timeEvent.text = searchMatch.strTime

        itemView.setOnClickListener {
            listener(searchMatch)
        }
    }
}