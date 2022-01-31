package com.raj.composepaginghilt.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.raj.composepaginghilt.data.local.dao.UnsplashImageDao
import com.raj.composepaginghilt.data.local.dao.UnsplashRemoteKeysDao
import com.raj.composepaginghilt.model.UnsplashImage
import com.raj.composepaginghilt.model.UnsplashRemoteKeys


@Database(entities = [UnsplashImage::class, UnsplashRemoteKeys::class], version = 1)
abstract class UnsplashDatabase : RoomDatabase() {

    abstract fun unsplashImageDao(): UnsplashImageDao
    abstract fun unsplashRemoteKeysDao(): UnsplashRemoteKeysDao

    //Databse Object creation was done in DatabaseModule

}