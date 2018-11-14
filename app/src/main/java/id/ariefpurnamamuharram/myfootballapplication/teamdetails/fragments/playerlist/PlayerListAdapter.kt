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
import id.ariefpurnamamuharram.myfootballapplication.model.PlayerList
import org.jetbrains.anko.find

class PlayerListAdapter(private val context: Context?,
                        private val player: List<PlayerList>,
                        private val listener: (PlayerList) -> Unit)
    : RecyclerView.Adapter<PlayerListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PlayerListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_player, parent, false)
    )

    override fun getItemCount(): Int = player.size

    override fun onBindViewHolder(holder: PlayerListViewHolder, position: Int) {
        holder.bindItem(player[position], listener)
    }

}

class PlayerListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val imgPlayer = view.find<ImageView>(R.id.img_player)
    private val playerName = view.find<TextView>(R.id.player_name)
    private val playerPosition = view.find<TextView>(R.id.player_position)

    fun bindItem(player: PlayerList, listener: (PlayerList) -> Unit) {
        Picasso.get().load(player.strCutout.toString()).into(imgPlayer)
        playerName.text = player.strPlayer
        playerPosition.text = player.strPosition

        itemView.setOnClickListener {
            listener(player)
        }
    }

}
