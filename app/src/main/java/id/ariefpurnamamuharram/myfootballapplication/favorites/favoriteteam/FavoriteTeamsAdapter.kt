package id.ariefpurnamamuharram.myfootballapplication.favorites

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import id.ariefpurnamamuharram.myfootballapplication.R
import id.ariefpurnamamuharram.myfootballapplication.model.FavoriteTeam
import org.jetbrains.anko.find

class FavoriteTeamsAdapter(private val context: Context?,
                           private val team: List<FavoriteTeam>,
                           private val listener: (FavoriteTeam) -> Unit) :
        RecyclerView.Adapter<FavoriteTeamsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            FavoriteTeamsViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_team, parent, false))

    override fun getItemCount(): Int = team.size

    override fun onBindViewHolder(holder: FavoriteTeamsViewHolder, position: Int) {
        holder.bindItem(team[position], listener)
    }
}

class FavoriteTeamsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val teamBadge = view.find<ImageView>(R.id.team_badge)
    private val teamName = view.find<TextView>(R.id.team_name)

    fun bindItem(team: FavoriteTeam, listener: (FavoriteTeam) -> Unit) {
        Picasso.get().load(team.strTeamBadge.toString()).into(teamBadge)
        teamName.text = team.strTeam

        itemView.setOnClickListener {
            listener(team)
        }
    }

}
