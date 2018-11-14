package id.ariefpurnamamuharram.myfootballapplication.model

import com.google.gson.annotations.SerializedName

data class PlayerDetailsResponse(
        @SerializedName("players")
        val playerDetails: List<PlayerDetails> = ArrayList()
)