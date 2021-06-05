package com.descifrador.jetpackcomposeplayground.util

interface EntityMapper<Entity, DomainModel> {
    fun mapFromEntity(entity: Entity): DomainModel

    fun mapFromDomainModel(domainModel: DomainModel): Entity

}
