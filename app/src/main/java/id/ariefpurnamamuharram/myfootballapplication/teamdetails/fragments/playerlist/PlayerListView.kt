package id.ariefpurnamamuharram.myfootballapplication.teamdetails.fragments.playerlist

import id.ariefpurnamamuharram.myfootballapplication.model.PlayerList

interface PlayerListView {
    fun showPlayerList(data: List<PlayerList>)
}