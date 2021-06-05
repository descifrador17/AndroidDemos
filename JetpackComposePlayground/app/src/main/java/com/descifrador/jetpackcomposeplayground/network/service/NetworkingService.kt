package com.descifrador.jetpackcomposeplayground.network.service

import com.descifrador.jetpackcomposeplayground.network.model.PokemonDetailsEntity
import retrofit2.http.GET
import retrofit2.http.Path

interface NetworkingService {

    @GET("{name}")
    suspend fun getPokemonDetails(@Path("name")name: String):PokemonDetailsEntity
}
