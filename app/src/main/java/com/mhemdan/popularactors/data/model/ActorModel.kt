package com.mhemdan.popularactors.data.model

data class ActorModel(
    val adult: Boolean,
    val id: Int,
    val known_for: List<KnownFor>,
    val name: String,
    val popularity: Double,
    val profile_path: String
)