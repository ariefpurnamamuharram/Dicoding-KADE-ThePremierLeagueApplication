package id.ariefpurnamamuharram.myfootballapplication.model

import com.google.gson.annotations.SerializedName

data class PlayerList(
        @SerializedName("idPlayer")
        var idPlayer: String? = null,

        @SerializedName("strPlayer")
        var strPlayer: String? = null,

        @SerializedName("strCutout")
        var strCutout: String? = null,

        @SerializedName("strPosition")
        var strPosition: String? = null
)