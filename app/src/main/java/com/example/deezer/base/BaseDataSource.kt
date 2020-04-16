package com.example.deezer.base

import com.example.models.domain.MResult
import retrofit2.Response
import timber.log.Timber

abstract class BaseDataSource {
    protected suspend fun <T> getResult(call: suspend () -> Response<T>): MResult<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return MResult.success(body)
            }
            return error(" ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): MResult<T> {
        Timber.e(message)
        return MResult.error("Network call has failed for a following reason: $message")
    }
}