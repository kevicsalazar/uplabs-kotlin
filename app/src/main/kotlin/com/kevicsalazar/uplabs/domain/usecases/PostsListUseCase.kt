package com.kevicsalazar.uplabs.domain.usecases

import com.kevicsalazar.uplabs.data.model.Post
import com.kevicsalazar.uplabs.domain.repository.PostsRepository
import io.reactivex.Observable
import javax.inject.Inject

/**
 * @author Kevin Salazar
 * @link kevicsalazar.com
 */
class PostsListUseCase @Inject constructor(val rep: PostsRepository) {

    fun getPostList(type: String): Observable<List<Post>> {
        return rep.getPostsList(type)
    }

}