package es.yeffry.cryptoappcompose.domain.repository

interface MapperToLocal<DomainEntity, DataEntity> {

    fun DomainEntity.toLocal(): DataEntity
}