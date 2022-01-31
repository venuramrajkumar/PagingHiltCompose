package com.raj.composepaginghilt.model

import androidx.room.Embedded
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(

    //Embedded should be used so that Rooms recoginize UserLinks Class in the response.
    @SerialName("links")
    @Embedded
    val userLinks: UserLinks,
    val username: String
)
