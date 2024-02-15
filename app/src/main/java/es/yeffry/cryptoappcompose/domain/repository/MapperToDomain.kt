package es.yeffry.cryptoappcompose.domain.repository

interface MapperToDomain<DataEntity, DomainEntity> {

    fun DataEntity.toDomain(): DomainEntity
}