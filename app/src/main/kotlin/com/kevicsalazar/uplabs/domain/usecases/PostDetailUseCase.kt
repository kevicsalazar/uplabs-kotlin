package com.kevicsalazar.uplabs.domain.usecases

import com.kevicsalazar.uplabs.data.model.Post
import com.kevicsalazar.uplabs.domain.repository.PostsRepository
import io.reactivex.Observable
import javax.inject.Inject

/**
 * @author Kevin Salazar
 * @link kevicsalazar.com
 */
class PostDetailUseCase @Inject constructor(val rep: PostsRepository) {

    fun getPostDetail(type: String, postId: String): Observable<Post> {
        return rep.getPostDetail(type, postId)
    }

}