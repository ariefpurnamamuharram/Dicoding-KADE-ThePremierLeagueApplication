package id.ariefpurnamamuharram.myfootballapplication.model

import com.google.gson.annotations.SerializedName

class PlayerListResponse(
        @SerializedName("player")
        val playerList: List<PlayerList> = ArrayList()
)