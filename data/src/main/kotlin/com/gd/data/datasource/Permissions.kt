package com.gd.data.datasource


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Permissions(
    @SerialName("admin")
    val admin: Boolean? = null,
    @SerialName("maintain")
    val maintain: Boolean? = null,
    @SerialName("pull")
    val pull: Boolean? = null,
    @SerialName("push")
    val push: Boolean? = null,
    @SerialName("triage")
    val triage: Boolean? = null
)