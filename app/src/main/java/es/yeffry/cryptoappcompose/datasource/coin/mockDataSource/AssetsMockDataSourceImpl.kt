package es.yeffry.cryptoappcompose.datasource.coin.mockDataSource

import es.yeffry.cryptoappcompose.datasource.coin.AssetsDataSourceInterface
import es.yeffry.cryptoappcompose.datasource.coin.remote.dto.AssetDto

class AssetsMockDataSourceImpl : AssetsDataSourceInterface {
    override suspend fun getAssets(): List<AssetDto> {
        return listOf(
            AssetDto(
                "bitcoin",
                "1",
                "BTC",
                "Bitcoin",
                "17193925.0000000000000000",
                "21000000.0000000000000000",
                "119150835874.4699281625807300",
                "2927959461.1750323310959460",
                "6929.8217756835584756",
                "-0.8101417214350335",
                "7175.0663247679233209"
            ),
            AssetDto(
                "ethereum",
                "2",
                "ETH",
                "Ethereum",
                "101160540.0000000000000000",
                null,
                "40967739219.6612727047843840",
                "1026669440.6451482672850841",
                "404.9774667045200896",
                "-0.0999626159535347",
                "415.3288028454417241"
            ),
            AssetDto(
                "ripple",
                "3",
                "XRP",
                "XRP",
                "39299874590.0000000000000000",
                "100000000000.0000000000000000",
                "16517228249.2902868380922380",
                "149328134.5032677889393019",
                "0.4202870472643482",
                "-1.9518258685302665",
                "0.4318239230821224"
            ),
            AssetDto(
                "bitcoin-cash",
                "4",
                "BCH",
                "Bitcoin Cash",
                "17278438.0000000000000000",
                "21000000.0000000000000000",
                "11902454455.1536127997298894",
                "287075418.5202079328968427",
                "688.8617162705108413",
                "-1.5016094894459434",
                "711.6276356693412774"
            ),
            AssetDto(
                "eos",
                "5",
                "EOS",
                "EOS",
                "906245118.0000000000000000",
                "1000000000.0000000000000000",
                "6327688685.5053582732768780",
                "373717579.0872289136334689",
                "6.9823147841833210",
                "-0.2487845516123365",
                "7.0345139617072947"
            ),
            AssetDto(
                "stellar",
                "6",
                "XLM",
                "Stellar",
                "18770261348.0000000000000000",
                null,
                "4395265468.8039656236913164",
                "28186508.6814478496347773",
                "0.2341611226032443",
                "-3.4735437955390772",
                "0.2412082330289685"
            ),
            AssetDto(
                "litecoin",
                "7",
                "LTC",
                "Litecoin",
                "57731482.0000000000000000",
                "84000000.0000000000000000",
                "4234484929.6430299360674272",
                "226037979.6802283949921417",
                "73.3479339685586096",
                "-1.3117992300270579",
                "75.1659221835912383"
            ),
            AssetDto(
                "cardano",
                "8",
                "ADA",
                "Cardano",
                "25927070538.0000000000000000",
                "45000000000.0000000000000000",
                "3342664439.1225859377289638",
                "32741914.1355823452856056",
                "0.1289256506716951",
                "0.0079476596654900",
                "0.1310244403993645"
            )
        )
    }
}