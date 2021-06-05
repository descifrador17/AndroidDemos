package com.descifrador.jetpackcomposeplayground.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonDetails(
    val abilities : List<Abilities> = listOf(),
    val height : Int? = 0,
    val id : Int? = 0,
    val name : String? = null,
    val stats : List<Stats> = listOf(),
    val weight : Int? = 0,
    val sprites : Sprites? = null
): Parcelable{
    @Parcelize
    data class Abilities(
        val ability : Ability? = null,
        val isHidden : Boolean? = null,
        val slot : Int? = 0
    ): Parcelable

    @Parcelize
    data class Ability(
        val name : String? = null,
        val url : String? = null
    ): Parcelable

    @Parcelize
    data class Stats(
        val base_stat : Int? = 0,
        val stat : Stat? = null
    ): Parcelable

    @Parcelize
    data class Stat(
        val name : String? = null,
        val url : String? = null
    ): Parcelable

    @Parcelize
    data class Sprites (
        val front_default : String? = null,
        val front_shiny : String? = null
    ): Parcelable

}

