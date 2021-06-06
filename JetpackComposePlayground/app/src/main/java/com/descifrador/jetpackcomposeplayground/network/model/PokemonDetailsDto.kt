package com.descifrador.jetpackcomposeplayground.network.model


data class PokemonDetailsDto(
    val abilities : List<AbilitiesEntity>? = null,
    val height : Int? = 0,
    val id : Int? = 0,
    val name : String? = null,
    val stats : List<StatsEntity>? = null,
    val weight : Int? = 0,
    val sprites : SpritesEntity? = null
){
    data class AbilitiesEntity(
        val ability : AbilityEntity? = null,
        val isHidden : Boolean? = null,
        val slot : Int? = 0
    )

    data class AbilityEntity(
        val name : String? = null,
        val url : String? = null
    )

    data class StatsEntity(
        val base_stat : Int? = 0,
        val stat : StatEntity? = null
    )

    data class StatEntity(
        val name : String? = null,
        val url : String? = null
    )


    data class SpritesEntity (
        val front_default : String? = null,
        val front_shiny : String? = null
    )

}



