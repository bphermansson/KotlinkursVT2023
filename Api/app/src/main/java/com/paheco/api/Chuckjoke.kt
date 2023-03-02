package com.paheco.api

import kotlinx.serialization.Serializable

@Serializable
data class Chuckjoke (val categories : List<String>, val created_at : String, val value : String)
