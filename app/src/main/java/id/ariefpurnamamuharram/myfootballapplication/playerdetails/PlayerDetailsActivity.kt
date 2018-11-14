package id.ariefpurnamamuharram.myfootballapplication.playerdetails

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import id.ariefpurnamamuharram.myfootballapplication.R
import id.ariefpurnamamuharram.myfootballapplication.api.ApiRepository
import id.ariefpurnamamuharram.myfootballapplication.model.PlayerDetails
import id.ariefpurnamamuharram.myfootballapplication.teamdetails.PlayerDetailsPresenter
import id.ariefpurnamamuharram.myfootballapplication.view.LoadingView
import org.jetbrains.anko.find

class PlayerDetailsActivity : AppCompatActivity(), LoadingView, PlayerDetailsView {

    private lateinit var presenter: PlayerDetailsPresenter

    private lateinit var idPlayer: String

    private lateinit var playerImage: ImageView
    private lateinit var playerPosition: TextView
    private lateinit var playerWeight: TextView
    private lateinit var playerHeight: TextView
    private lateinit var playerDesc: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_details)

        playerImage = find(R.id.img_player)
        playerPosition = find(R.id.player_position)
        playerWeight = find(R.id.player_weight)
        playerHeight = find(R.id.player_height)
        playerDesc = find(R.id.player_desc)

        idPlayer = intent.getStringExtra("idPlayer")

        val gson = Gson()
        val request = ApiRepository()

        presenter = PlayerDetailsPresenter(this, this, request, gson)
        presenter.getPlayerDetails(idPlayer)
    }

    override fun showLoading() {}

    override fun hideLoading() {}

    override fun showPlayerDetails(data: List<PlayerDetails>) {
        supportActionBar?.title = data[0].strPlayer
        Picasso.get().load(data[0].strCutout).into(playerImage)
        playerPosition.text = data[0].strPosition
        playerWeight.text = data[0].strWeight
        playerHeight.text = data[0].strHeight
        playerDesc.text = data[0].strDescriptionEN
    }
}