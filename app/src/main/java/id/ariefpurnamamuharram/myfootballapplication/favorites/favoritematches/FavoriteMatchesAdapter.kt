package id.ariefpurnamamuharram.myfootballapplication.favorites.favoritematches

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import id.ariefpurnamamuharram.myfootballapplication.R
import id.ariefpurnamamuharram.myfootballapplication.R.id.*
import id.ariefpurnamamuharram.myfootballapplication.model.FavoriteMatch
import id.ariefpurnamamuharram.myfootballapplication.util.invisible
import org.jetbrains.anko.find

class FavoriteMatchesAdapter(private val context: Context?,
                             private val matches: List<FavoriteMatch>,
                             private val listener: (FavoriteMatch) -> Unit) :
        RecyclerView.Adapter<FavoriteMatchesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            FavoriteMatchesViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_match, parent, false))

    override fun getItemCount(): Int = matches.size

    override fun onBindViewHolder(holder: FavoriteMatchesViewHolder, position: Int) {
        holder.bindItem(matches[position], listener)
    }
}

class FavoriteMatchesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val dateEvent = view.find<TextView>(match_date)
    private val timeEvent = view.find<TextView>(match_time)
    private val homeTeam = view.find<TextView>(home_team)
    private val awayTeam = view.find<TextView>(away_team)
    private val homeScore = view.find<TextView>(home_score)
    private val awayScore = view.find<TextView>(away_score)
    private val addReminder = view.find<ImageButton>(add_reminder)

    fun bindItem(item: FavoriteMatch, listener: (FavoriteMatch) -> Unit) {
        dateEvent.text = item.dateEvent
        timeEvent.text = item.strTime
        homeTeam.text = item.strHomeTeam
        awayTeam.text = item.strAwayTeam
        homeScore.text = item.intHomeScore
        awayScore.text = item.intAwayScore

        // Disable addReminder button
        addReminder.isEnabled = false
        addReminder.invisible()

        itemView.setOnClickListener {
            listener(item)
        }
    }

}
