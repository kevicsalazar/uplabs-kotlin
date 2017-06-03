package com.kevicsalazar.uplabs.data

import com.kevicsalazar.uplabs.data.repository.PostsDataRepository
import com.kevicsalazar.uplabs.domain.repository.PostsRepository
import dagger.Binds
import dagger.Module

/**
 * @author Kevin Salazar
 * @link kevicsalazar.com
 */
@Module
abstract class DataModule {

    @Binds abstract fun bindPostsRepository(repository: PostsDataRepository): PostsRepository

}