package com.descifrador.jetpackcomposeplayground.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonsList (
    val count:Int? = 0,
    val nextLink:String? = null,
    val prevLink:String? = null,
    val pokemons:List<Pokemon> = listOf()
):Parcelable{

    @Parcelize
    data class Pokemon(
        val name: String? = null,
        val url: String? = null
    ): Parcelable

}
