package id.ariefpurnamamuharram.myfootballapplication.api

import id.ariefpurnamamuharram.myfootballapplication.BuildConfig.BASE_URL
import id.ariefpurnamamuharram.myfootballapplication.BuildConfig.TSDB_API_KEY

object TheSportDBApi {

    fun getLastMatches(league: String?): String = BASE_URL +
            "api/v1/json/$TSDB_API_KEY/eventspastleague.php?id=" + league

    fun getNextMatches(league: String?): String = BASE_URL +
            "api/v1/json/$TSDB_API_KEY/eventsnextleague.php?id=" + league

    fun getEventDetails(idEvent: String?): String = BASE_URL +
            "api/v1/json/$TSDB_API_KEY/lookupevent.php?id=" + idEvent

    fun getTeamBadge(team: String?): String = BASE_URL +
            "api/v1/json/$TSDB_API_KEY/lookupteam.php?id=" + team

    fun getEventByName(name: String?): String = BASE_URL +
            "api/v1/json/$TSDB_API_KEY/searchevents.php?e=" + name

    fun getListAllTeams(league: String?): String = BASE_URL +
            "api/v1/json/$TSDB_API_KEY/lookup_all_teams.php?id=" + league

    fun getTeamByName(name: String?): String = BASE_URL +
            "api/v1/json/$TSDB_API_KEY/searchteams.php?t=" + name

    fun getTeamDetails(team: String?): String = BASE_URL +
            "api/v1/json/$TSDB_API_KEY/lookupteam.php?id=" + team

    fun getPlayerList(idTeam: String?): String = BASE_URL +
            "api/v1/json/$TSDB_API_KEY/lookup_all_players.php?id=" + idTeam

    fun getPlayerDetails(idPlayer: String?): String = BASE_URL +
            "api/v1/json/$TSDB_API_KEY/lookupplayer.php?id=" + idPlayer

}