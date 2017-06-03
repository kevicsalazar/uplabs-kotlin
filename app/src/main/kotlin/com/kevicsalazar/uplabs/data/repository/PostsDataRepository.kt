package com.kevicsalazar.uplabs.data.repository

import com.kevicsalazar.uplabs.data.model.Post
import com.kevicsalazar.uplabs.data.sources.cloud.PostsRestService
import com.kevicsalazar.uplabs.domain.repository.PostsRepository
import io.reactivex.Observable
import javax.inject.Inject

/**
 * @author Kevin Salazar
 * @link kevicsalazar.com
 */
class PostsDataRepository @Inject constructor(val rs: PostsRestService) : PostsRepository {

    override fun getPostsList(type: String): Observable<List<Post>> {
        return rs.getPosts(type)
    }
}