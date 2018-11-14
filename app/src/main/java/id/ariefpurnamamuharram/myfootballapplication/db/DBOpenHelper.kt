package id.ariefpurnamamuharram.myfootballapplication.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import id.ariefpurnamamuharram.myfootballapplication.model.FavoriteMatch
import id.ariefpurnamamuharram.myfootballapplication.model.FavoriteTeam
import org.jetbrains.anko.db.*

class DBOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "Favorite.db",
        null, 1) {

    companion object {
        private var instance: DBOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): DBOpenHelper {
            if (instance == null) {
                instance = DBOpenHelper(ctx.applicationContext)
            }
            return instance as DBOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(FavoriteMatch.TABLE_FAVORITE, true,
                FavoriteMatch.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                FavoriteMatch.ID_EVENT to TEXT + UNIQUE,
                FavoriteMatch.ID_HOME_TEAM to TEXT,
                FavoriteMatch.ID_AWAY_TEAM to TEXT,
                FavoriteMatch.DATE_EVENT to TEXT,
                FavoriteMatch.STR_TIME to TEXT,
                FavoriteMatch.STR_HOME_TEAM to TEXT,
                FavoriteMatch.STR_AWAY_TEAM to TEXT,
                FavoriteMatch.INT_HOME_SCORE to TEXT,
                FavoriteMatch.INT_AWAY_SCORE to TEXT)

        db.createTable(FavoriteTeam.TABLE_FAVORITE_TEAM, true,
                FavoriteTeam.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                FavoriteTeam.ID_TEAM to TEXT + UNIQUE,
                FavoriteTeam.STR_TEAM to TEXT,
                FavoriteTeam.STR_TEAM_BADGE to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(FavoriteMatch.TABLE_FAVORITE, true)
        db.dropTable(FavoriteTeam.TABLE_FAVORITE_TEAM, true)
    }
}

// Access property for context
val Context.database: DBOpenHelper
    get() = DBOpenHelper.getInstance(applicationContext)