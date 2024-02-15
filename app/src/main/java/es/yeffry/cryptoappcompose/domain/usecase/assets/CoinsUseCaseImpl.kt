package es.yeffry.cryptoappcompose.domain.usecase.assets

import es.yeffry.cryptoappcompose.domain.entities.Coin
import es.yeffry.cryptoappcompose.domain.repository.CoinsRepository

class CoinsUseCaseImpl(private val coinsRepository: CoinsRepository): CoinsUseCase {
    override suspend fun getCoins(): List<Coin> {
        val res = coinsRepository.getCoins()

        return res?: listOf()
    }
}