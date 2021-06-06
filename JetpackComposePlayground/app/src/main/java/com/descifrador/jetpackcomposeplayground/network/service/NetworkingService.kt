package com.descifrador.jetpackcomposeplayground.network.service

import com.descifrador.jetpackcomposeplayground.network.model.PokemonDetailsDto
import com.descifrador.jetpackcomposeplayground.network.model.PokemonsListDto
import retrofit2.http.GET
import retrofit2.http.Path

interface NetworkingService {

    @GET("pokemon/{name}")
    suspend fun getPokemonDetails(@Path("name")name: String):PokemonDetailsDto

    @GET("pokemon/")
    suspend fun getPokemonList():PokemonsListDto
}
