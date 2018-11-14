package id.ariefpurnamamuharram.myfootballapplication.model

data class FavoriteMatch(val id: Long?,
                         val idEvent: String?,
                         val idHomeTeam: String?,
                         val idAwayTeam: String?,
                         val dateEvent: String?,
                         val strTime: String?,
                         val strHomeTeam: String?,
                         val strAwayTeam: String?,
                         val intHomeScore: String?,
                         val intAwayScore: String?) {

    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID: String = "ID_"
        const val ID_EVENT = "ID_EVENT"
        const val ID_HOME_TEAM = "ID_HOME_TEAM"
        const val ID_AWAY_TEAM = "ID_AWAY_TEAM"
        const val DATE_EVENT = "DATE_EVENT"
        const val STR_TIME = "STR_TIME"
        const val STR_HOME_TEAM = "STR_HOME_TEAM"
        const val STR_AWAY_TEAM = "STR_AWAY_TEAM"
        const val INT_HOME_SCORE = "INT_HOME_SCORE"
        const val INT_AWAY_SCORE = "INT_AWAY_SCORE"
    }

}