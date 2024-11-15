package com.art.searchengine

import com.google.gson.annotations.SerializedName

data class RickAndMortyDataResponse(@SerializedName("results") val rickAndMorty: List<RickAndMortyResponse>)

data class RickAndMortyResponse(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("image") val image: String,
    @SerializedName("species") val species: String,
    @SerializedName("status") val status: String,
)