package es.yeffry.cryptoappcompose.network

import com.google.gson.Gson
import es.yeffry.cryptoappcompose.domain.entities.CustomException
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject

class NetworkManager @Inject constructor() {
    suspend fun <T> load(call: suspend () -> Response<T>): Result<T?> {
        var serverResponse = ErrorBodyDTO("")
        return try {
            val response = call()
            if (response.isSuccessful) {
                Result.success(response.body())
            } else {
                val gson = Gson()
                val errorBody = response.errorBody()
                val errorBodyDTO = if (errorBody != null) {
                    gson.fromJson(errorBody.string(), ErrorBodyDTO::class.java)
                } else {
                    ErrorBodyDTO("")
                }
                Result.failure(CustomException(errorBodyDTO.errorMessage))
            }
        } catch (e: Exception) {
            val errorMessage = when (e) {
                is HttpException -> {
                    val errorBody = e.response()?.errorBody()
                    if (errorBody != null) {
                        val gson = Gson()
                        val errorBodyDTO =
                            gson.fromJson(errorBody.string(), ErrorBodyDTO::class.java)
                        errorBodyDTO.errorMessage
                    } else {
                        serverResponse.errorMessage
                    }
                }

                is SocketTimeoutException -> "Timeout Error"
                is IOException -> "Thread Error"
                else -> "Unknown Error"
            }
            Result.failure(CustomException(errorMessage))
        }
    }
}