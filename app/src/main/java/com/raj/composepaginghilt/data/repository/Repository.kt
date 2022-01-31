package com.raj.composepaginghilt.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.raj.composepaginghilt.data.local.UnsplashDatabase
import com.raj.composepaginghilt.data.paging.SearchPagingSource
import com.raj.composepaginghilt.data.paging.UnsplashRemoteMediator
import com.raj.composepaginghilt.data.remote.UnsplashApi
import com.raj.composepaginghilt.model.UnsplashImage
import com.raj.composepaginghilt.utils.Constants.ITEMS_PER_PAGE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class Repository @Inject constructor(
    private val unsplashDatabase: UnsplashDatabase,
    private val unsplashApi: UnsplashApi
) {
    @ExperimentalPagingApi
    fun getAllImages(): Flow<PagingData<UnsplashImage>> {
        val pagingSourceFactory = { unsplashDatabase.unsplashImageDao().getAllImages() }
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = UnsplashRemoteMediator(
                unsplashApi = unsplashApi,
                unsplashDatabase = unsplashDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    fun searchImages(query: String): Flow<PagingData<UnsplashImage>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            pagingSourceFactory = {
                //We are not using Remote mediator for this search call.
                SearchPagingSource(unsplashApi = unsplashApi, query = query)
            }
        ).flow
    }
}