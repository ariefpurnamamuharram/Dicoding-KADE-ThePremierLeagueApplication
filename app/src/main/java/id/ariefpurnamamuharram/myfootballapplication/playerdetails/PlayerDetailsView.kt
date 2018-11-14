package id.ariefpurnamamuharram.myfootballapplication.playerdetails

import id.ariefpurnamamuharram.myfootballapplication.model.PlayerDetails

interface PlayerDetailsView {
    fun showPlayerDetails(data: List<PlayerDetails>)
}