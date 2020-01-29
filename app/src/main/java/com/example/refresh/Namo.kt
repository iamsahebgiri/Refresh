package com.example.refresh
import com.google.gson.annotations.SerializedName

data class Namo(
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("_id")
    val id: String,
    @SerializedName("updatedAt")
    val updatedAt: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("__v")
    val v: Int
)