package com.descifrador.jetpackcomposeplayground.mappers


import com.descifrador.jetpackcomposeplayground.domain.model.PokemonsList
import com.descifrador.jetpackcomposeplayground.network.model.PokemonsListDto
import com.descifrador.jetpackcomposeplayground.util.DataClassMapper
import com.descifrador.jetpackcomposeplayground.util.DtoMapper

class PokemonsListDtoMapper: DtoMapper<PokemonsListDto, PokemonsList> {
    override fun mapToDomainModel(dto: PokemonsListDto): PokemonsList {
        val pokemonsMapper = DataClassMapper<PokemonsListDto.PokemonEntity,
                PokemonsList.Pokemon>()

        var mappedPokemons = mutableListOf<PokemonsList.Pokemon>()
        for ( i in dto.pokemons){
            mappedPokemons.add(pokemonsMapper(i))
        }

        return PokemonsList(
            count = dto.count,
            nextLink = dto.nextLink,
            prevLink = dto.prevLink,
            pokemons = mappedPokemons
        )
    }

    override fun mapFromDomainModel(domainModel: PokemonsList): PokemonsListDto {
        val pokemonsEntityMapper = DataClassMapper<PokemonsList.Pokemon,
                PokemonsListDto.PokemonEntity>()

        var mappedPokemonsEntity = mutableListOf<PokemonsListDto.PokemonEntity>()
        for ( i in domainModel.pokemons){
            mappedPokemonsEntity.add(pokemonsEntityMapper(i))
        }

        return PokemonsListDto(
            count = domainModel.count,
            nextLink = domainModel.nextLink,
            prevLink = domainModel.prevLink,
            pokemons = mappedPokemonsEntity
        )
    }
}
