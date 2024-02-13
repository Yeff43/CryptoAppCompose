package es.yeffry.cryptoappcompose.network

import com.google.gson.annotations.SerializedName

class ErrorBodyDTO (
    @SerializedName("error")
    val errorMessage: String
)