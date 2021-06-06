package com.descifrador.jetpackcomposeplayground.network.model

import com.google.gson.annotations.SerializedName


data class PokemonsListDto (
    val count:Int? = 0,
    @SerializedName("next")
    val nextLink:String? = null,
    @SerializedName("prev")
    val prevLink:String? = null,
    @SerializedName("results")
    val pokemons:List<PokemonEntity> = listOf()
){

    data class PokemonEntity(
        val name: String? = null,
        val url: String? = null
    )

}
