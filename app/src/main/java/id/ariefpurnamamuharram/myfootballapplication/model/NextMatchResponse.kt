package id.ariefpurnamamuharram.myfootballapplication.model

import com.google.gson.annotations.SerializedName

data class NextMatchResponse(
        @SerializedName("events")
        val nextMatch: List<NextMatch> = ArrayList()
)