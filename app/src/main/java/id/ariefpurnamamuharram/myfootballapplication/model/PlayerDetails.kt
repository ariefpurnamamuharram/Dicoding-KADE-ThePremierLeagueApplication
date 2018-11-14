package id.ariefpurnamamuharram.myfootballapplication.model

import com.google.gson.annotations.SerializedName

data class PlayerDetails(
        @SerializedName("idPlayer")
        var idPlayer: String? = null,

        @SerializedName("strPlayer")
        var strPlayer: String? = null,

        @SerializedName("strCutout")
        var strCutout: String? = null,

        @SerializedName("strPosition")
        var strPosition: String? = null,

        @SerializedName("strWeight")
        var strWeight: String? = null,

        @SerializedName("strHeight")
        var strHeight: String? = null,

        @SerializedName("strDescriptionEN")
        var strDescriptionEN: String? = null
)