package com.gd.data.datasource


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CustomProperties(
    @SerialName("CodeReviewPolicy")
    val codeReviewPolicy: String? = null,
    @SerialName("SensitivityLevel")
    val sensitivityLevel: String? = null
)