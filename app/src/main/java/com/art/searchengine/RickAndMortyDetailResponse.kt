package com.art.searchengine

import com.google.gson.annotations.SerializedName

data class RickAndMortyDetailResponse(
    @SerializedName("name") val name: String,
    @SerializedName("image") val image: String,
    @SerializedName("status") val status: String,
    @SerializedName("species") val species: String,
    @SerializedName("location") val location: LocationResponse,
    @SerializedName("origin") val origin: OriginResponse
)

data class LocationResponse(
    @SerializedName("name") val locationName: String,
    @SerializedName("url") val locationImage: String,
)

data class OriginResponse(
    @SerializedName("name") val originName: String,
)

