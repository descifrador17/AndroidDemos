package com.descifrador.jetpackcomposeplayground.util

interface DtoMapper<T, DomainModel> {
    fun mapToDomainModel(entity: T): DomainModel

    fun mapFromDomainModel(domainModel: DomainModel): T

}
