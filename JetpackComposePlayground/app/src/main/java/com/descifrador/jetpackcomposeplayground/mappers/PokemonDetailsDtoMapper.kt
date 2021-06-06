package com.descifrador.jetpackcomposeplayground.mappers

import com.descifrador.jetpackcomposeplayground.domain.model.PokemonDetails
import com.descifrador.jetpackcomposeplayground.network.model.PokemonDetailsDto
import com.descifrador.jetpackcomposeplayground.util.DataClassMapper
import com.descifrador.jetpackcomposeplayground.util.DtoMapper

class PokemonDetailsDtoMapper: DtoMapper<PokemonDetailsDto, PokemonDetails> {
    override fun mapToDomainModel(dto: PokemonDetailsDto): PokemonDetails {

        val spritesMapper = DataClassMapper<PokemonDetailsDto.SpritesEntity,
                PokemonDetails.Sprites>()

        val mappedSprites = spritesMapper(dto.sprites!!)

        var mappedStats = mutableListOf<PokemonDetails.Stats>()
        for( i in dto.stats!!){
            val statMapper = DataClassMapper<PokemonDetailsDto.StatEntity,
                    PokemonDetails.Stat>()
            val statsMapper = DataClassMapper<PokemonDetailsDto.StatsEntity,
                    PokemonDetails.Stats>().register("stat",statMapper)

            mappedStats.add(statsMapper(i))
        }

        var mappedAbilities = mutableListOf<PokemonDetails.Abilities>()
        for( i in dto.abilities!!){
            val abilityMapper = DataClassMapper<PokemonDetailsDto.AbilityEntity,
                    PokemonDetails.Ability>()
            val abilitiesMapper = DataClassMapper<PokemonDetailsDto.AbilitiesEntity,
                    PokemonDetails.Abilities>().register("ability",abilityMapper)

            mappedAbilities.add(abilitiesMapper(i))
        }

        return PokemonDetails(
            abilities = mappedAbilities,
            height = dto.height,
            id = dto.id,
            name = dto.name,
            stats = mappedStats,
            weight = dto.weight,
            sprites = mappedSprites
        )
    }

    override fun mapFromDomainModel(domainModel: PokemonDetails): PokemonDetailsDto {
        val spritesEntityMapper = DataClassMapper<PokemonDetails.Sprites,
                PokemonDetailsDto.SpritesEntity>()

        val mappedSpritesEntity = spritesEntityMapper(domainModel.sprites!!)

        var mappedStatsEntity = mutableListOf<PokemonDetailsDto.StatsEntity>()
        for( i in domainModel.stats!!){
            val statEntityMapper = DataClassMapper<PokemonDetails.Stat,
                    PokemonDetailsDto.StatEntity>()
            val statsEntityMapper = DataClassMapper<PokemonDetails.Stats,
                    PokemonDetailsDto.StatsEntity>().register("stat",statEntityMapper)

            mappedStatsEntity.add(statsEntityMapper(i))
        }

        var mappedAbilitiesEntity = mutableListOf<PokemonDetailsDto.AbilitiesEntity>()
        for( i in domainModel.abilities!!){
            val abilityEntityMapper = DataClassMapper<PokemonDetails.Ability,
                    PokemonDetailsDto.AbilityEntity>()
            val abilitiesEntityMapper = DataClassMapper<PokemonDetails.Abilities,
                    PokemonDetailsDto.AbilitiesEntity>().register("ability",abilityEntityMapper)

            mappedAbilitiesEntity.add(abilitiesEntityMapper(i))
        }

        return PokemonDetailsDto(
            abilities = mappedAbilitiesEntity,
            height = domainModel.height,
            id = domainModel.id,
            name = domainModel.name,
            stats = mappedStatsEntity,
            weight = domainModel.weight,
            sprites = mappedSpritesEntity
        )
    }
}
