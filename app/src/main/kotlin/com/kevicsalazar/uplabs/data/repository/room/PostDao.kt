package com.kevicsalazar.uplabs.data.repository.room

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.kevicsalazar.uplabs.data.model.Post
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * @author Kevin Salazar
 * @link kevicsalazar.com
 */
@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun savePosts(posts: List<Post>)

    @Query("SELECT * FROM Post WHERE platform = :platform")
    fun getPosts(platform: String): Flowable<List<Post>>

    @Query("SELECT * FROM Post WHERE id = :id")
    fun getPost(id: String): Flowable<Post>

}