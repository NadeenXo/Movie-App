package com.example.movieretrokot.Data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.movieretrokot.Movie.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class AppDB: RoomDatabase() {
    abstract fun movieDao():AppDao
    companion object{
        @Volatile //visible to other threads
        private var INSTANCE:AppDB?=null
        fun getDB(context: Context):AppDB{
            val tempInstance = INSTANCE
            if (tempInstance!=null){
                return tempInstance
            }
            //protected from conc exec from multi threads
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDB::class.java,
                    "movie_database"
                )
                    //.allowMainThreadQueries()
                    //.fallbackToDestructiveMigration()   //when incrementing version
                    .build()
                INSTANCE=instance
                return instance
            }
        }
    }
}