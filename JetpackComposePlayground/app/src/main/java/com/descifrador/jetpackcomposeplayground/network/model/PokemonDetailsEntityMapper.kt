package com.descifrador.jetpackcomposeplayground.network.model

import com.descifrador.jetpackcomposeplayground.domain.model.PokemonDetails
import com.descifrador.jetpackcomposeplayground.util.DataClassMapper
import com.descifrador.jetpackcomposeplayground.util.EntityMapper

class PokemonDetailsEntityMapper: EntityMapper<PokemonDetailsEntity, PokemonDetails> {
    override fun mapFromEntity(entity: PokemonDetailsEntity): PokemonDetails {

        val spritesMapper = DataClassMapper<PokemonDetailsEntity.SpritesEntity,
                PokemonDetails.Sprites>()
        val mappedSprites = PokemonDetails.Sprites(front_default = entity.sprites!!
        .front_default, front_shiny = entity.sprites!!.front_shiny)

        var mappedStats = mutableListOf<PokemonDetails.Stats>()
        for( i in entity.stats!!){
            val statMapper = DataClassMapper<PokemonDetailsEntity.StatEntity,
                    PokemonDetails.Stat>()
            val statsMapper = DataClassMapper<PokemonDetailsEntity.StatsEntity,
                    PokemonDetails.Stats>().register("stat",statMapper)

            mappedStats.add(statsMapper(i))
        }

        var mappedAbilities = mutableListOf<PokemonDetails.Abilities>()
        for( i in entity.abilities!!){
            val abilityMapper = DataClassMapper<PokemonDetailsEntity.AbilityEntity,
                    PokemonDetails.Ability>()
            val abilitiesMapper = DataClassMapper<PokemonDetailsEntity.AbilitiesEntity,
                    PokemonDetails.Abilities>().register("ability",abilityMapper)

            mappedAbilities.add(abilitiesMapper(i))
        }

        return PokemonDetails(
            abilities = mappedAbilities,
            height = entity.height,
            id = entity.id,
            name = entity.name,
            stats = mappedStats,
            weight = entity.weight,
            sprites = mappedSprites
        )
    }

    override fun mapFromDomainModel(domainModel: PokemonDetails): PokemonDetailsEntity {
        TODO("Not yet implemented")
    }
}
