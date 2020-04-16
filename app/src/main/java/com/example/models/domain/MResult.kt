package com.example.models.domain

data class MResult<out T>(val status: Status, val data: T?, val message: String?) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> success(data: T): MResult<T> {
            return MResult(Status.SUCCESS, data, null)
        }

        fun <T> error(message: String, data: T? = null): MResult<T> {
            return MResult(Status.ERROR, data, message)
        }

        fun <T> loading(data: T? = null): MResult<T> {
            return MResult(Status.LOADING, data, null)
        }
    }
}