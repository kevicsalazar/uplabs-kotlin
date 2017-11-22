package com.kevicsalazar.uplabs.data.repository.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.kevicsalazar.uplabs.data.model.Post
import com.kevicsalazar.uplabs.data.model.Submitter

/**
 * @author Kevin Salazar
 * @link kevicsalazar.com
 */
@Database(entities = arrayOf(Post::class, Submitter::class), version = 1, exportSchema = false)
abstract class PostsDatabase : RoomDatabase() {

    abstract fun postDao(): PostDao

    companion object {

        fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext,
                        PostsDatabase::class.java, "UpLabs.db")
                        .build()
    }

}