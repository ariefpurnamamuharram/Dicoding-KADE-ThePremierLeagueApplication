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
import id.ariefpurnamamuharram.myfootballapplication.model.ListAllTeams
import org.jetbrains.anko.find

class MainTeamsAdapter(private val context: Context?,
                       private val listAllTeams: List<ListAllTeams>,
                       private val listener: (ListAllTeams) -> Unit)
    : RecyclerView.Adapter<ListAllTeamsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ListAllTeamsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_team, parent, false)
    )

    override fun getItemCount(): Int = listAllTeams.size

    override fun onBindViewHolder(holder: ListAllTeamsViewHolder, position: Int) {
        holder.bindItem(listAllTeams[position], listener)
    }

}

class ListAllTeamsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val teamBadge = view.find<ImageView>(R.id.team_badge)
    private val teamName = view.find<TextView>(R.id.team_name)

    fun bindItem(listAllTeams: ListAllTeams, listener: (ListAllTeams) -> Unit) {
        Picasso.get().load(listAllTeams.strTeamBadge.toString()).into(teamBadge)
        teamName.text = listAllTeams.strTeam

        itemView.setOnClickListener {
            listener(listAllTeams)
        }
    }

}
