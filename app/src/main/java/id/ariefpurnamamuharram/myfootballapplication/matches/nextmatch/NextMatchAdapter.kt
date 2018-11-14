package id.ariefpurnamamuharram.myfootballapplication.matches.nextmatch

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import id.ariefpurnamamuharram.myfootballapplication.R
import id.ariefpurnamamuharram.myfootballapplication.model.NextMatch
import org.jetbrains.anko.find
import java.text.SimpleDateFormat
import java.util.*

class NextMatchAdapter(private val context: Context?,
                       private val nextMatch: List<NextMatch>,
                       private val listener: (NextMatch) -> Unit)
    : RecyclerView.Adapter<NextMatchViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int) = NextMatchViewHolder(LayoutInflater.from(p0.context)
            .inflate(R.layout.item_match, p0, false))

    override fun getItemCount(): Int = nextMatch.size

    override fun onBindViewHolder(p0: NextMatchViewHolder, p1: Int) {
        p0.bindItem(nextMatch[p1], listener)
    }

}

class NextMatchViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val homeTeam = view.find<TextView>(R.id.home_team)
    private val awayTeam = view.find<TextView>(R.id.away_team)
    private val dateEvent = view.find<TextView>(R.id.match_date)
    private val timeEvent = view.find<TextView>(R.id.match_time)

    @SuppressLint("SimpleDateFormat")
    fun bindItem(nextMatch: NextMatch, listener: (NextMatch) -> Unit) {

        // Convert timezone to Asia/Jakarta
        val oldFormatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        oldFormatter.timeZone = TimeZone.getTimeZone("UTC")
        val dateFormatter = SimpleDateFormat("dd/MM/yyyy")
        val timeFormatter = SimpleDateFormat("HH:mm")

        homeTeam.text = nextMatch.strHomeTeam
        awayTeam.text = nextMatch.strAwayTeam
        dateEvent.text = dateFormatter.format(oldFormatter.parse(nextMatch.dateEvent + " " + nextMatch.strTime))
        timeEvent.text = timeFormatter.format(oldFormatter.parse(nextMatch.dateEvent + " " + nextMatch.strTime))

        itemView.setOnClickListener {
            listener(nextMatch)
        }
    }
}