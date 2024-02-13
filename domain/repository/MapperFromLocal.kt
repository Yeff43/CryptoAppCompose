package es.yeffry.cryptoappcompose.domain.repository

interface MapperFromLocal<DomainEntity, DataEntity> {

    fun DataEntity.fromLocal(): DomainEntity
}