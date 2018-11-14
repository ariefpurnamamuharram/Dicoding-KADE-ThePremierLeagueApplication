package id.ariefpurnamamuharram.myfootballapplication.teams

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import id.ariefpurnamamuharram.myfootballapplication.R
import id.ariefpurnamamuharram.myfootballapplication.model.TeamByName
import org.jetbrains.anko.find

class SearchTeamsAdapter(private val context: Context?,
                         private val listTeamsByName: List<TeamByName>,
                         private val listener: (TeamByName) -> Unit)
    : RecyclerView.Adapter<ListTeamsByNameViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ListTeamsByNameViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_team, parent, false)
    )

    override fun getItemCount(): Int = listTeamsByName.size

    override fun onBindViewHolder(holder: ListTeamsByNameViewHolder, position: Int) {
        holder.bindItem(listTeamsByName[position], listener)
    }

}

class ListTeamsByNameViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val teamBadge = view.find<ImageView>(R.id.team_badge)
    private val teamName = view.find<TextView>(R.id.team_name)

    fun bindItem(listTeamsByName: TeamByName, listener: (TeamByName) -> Unit) {
        Picasso.get().load(listTeamsByName.strTeamBadge.toString()).into(teamBadge)
        teamName.text = listTeamsByName.strTeam

        itemView.setOnClickListener {
            listener(listTeamsByName)
        }
    }

}
