package id.ariefpurnamamuharram.myfootballapplication.matches.lastmatch

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import id.ariefpurnamamuharram.myfootballapplication.R
import id.ariefpurnamamuharram.myfootballapplication.model.LastMatch
import id.ariefpurnamamuharram.myfootballapplication.util.invisible
import org.jetbrains.anko.find
import java.text.SimpleDateFormat
import java.util.*

class LastMatchAdapter(private val context: Context?,
                       private val lastMatch: List<LastMatch>,
                       private val listener: (LastMatch) -> Unit)
    : RecyclerView.Adapter<LastMatchViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int) = LastMatchViewHolder(LayoutInflater.from(p0.context)
            .inflate(R.layout.item_match, p0, false))

    override fun getItemCount(): Int = lastMatch.size

    override fun onBindViewHolder(p0: LastMatchViewHolder, p1: Int) {
        p0.bindItem(lastMatch[p1], listener)
    }

}

class LastMatchViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val homeTeam = view.find<TextView>(R.id.home_team)
    private val awayTeam = view.find<TextView>(R.id.away_team)
    private val homeScore = view.find<TextView>(R.id.home_score)
    private val awayScore = view.find<TextView>(R.id.away_score)
    private val dateEvent = view.find<TextView>(R.id.match_date)
    private val timeEvent = view.find<TextView>(R.id.match_time)
    private val addReminder = view.find<ImageButton>(R.id.add_reminder)

    @SuppressLint("SimpleDateFormat")
    fun bindItem(lastMatch: LastMatch, listener: (LastMatch) -> Unit) {

        // Convert timezone to Asia/Jakarta
        val oldFormatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        oldFormatter.timeZone = TimeZone.getTimeZone("UTC")
        val dateFormatter = SimpleDateFormat("dd/MM/yyyy")
        val timeFormatter = SimpleDateFormat("HH:mm")

        homeTeam.text = lastMatch.strHomeTeam
        awayTeam.text = lastMatch.strAwayTeam
        homeScore.text = lastMatch.intHomeScore
        awayScore.text = lastMatch.intAwayScore
        dateEvent.text = dateFormatter.format(oldFormatter.parse(lastMatch.dateEvent + " " + lastMatch.strTime))
        timeEvent.text = timeFormatter.format(oldFormatter.parse(lastMatch.dateEvent + " " + lastMatch.strTime))

        itemView.setOnClickListener {
            listener(lastMatch)
        }

        addReminder.isEnabled = false
        addReminder.setImageResource(R.drawable.ic_add_reminder_disabled)

        // Hide addReminder button
        addReminder.invisible()
    }
}