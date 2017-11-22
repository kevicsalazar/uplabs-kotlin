package com.kevicsalazar.uplabs.data.repository.room

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.kevicsalazar.uplabs.data.model.Post
import io.reactivex.Flowable

/**
 * @author Kevin Salazar
 * @link kevicsalazar.com
 */
@Dao
interface PostDao {

    @Query("SELECT * FROM Post")
    fun getPostsList(): Flowable<List<Post>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inserPostList(posts: List<Post>)

    @Query("SELECT * FROM Post WHERE id = :id")
    fun getPostById(id: String): Flowable<Post>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPost(post: Post)

}