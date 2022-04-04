package com.example.evotor.goods.utils

data class Event<out T>(val status: Status, val data: T?, val throwable: Throwable?) {

    companion object {
        fun <T> loading(): Event<T> {
            return Event(
                Status.LOADING,
                null,
                null
            )
        }

        fun <T> success(data: T? = null): Event<T> {
            return Event(
                Status.SUCCESS,
                data,
                null
            )
        }

        fun <T> error(throwable: Throwable): Event<T> {
            return Event(
                Status.ERROR,
                null,
                throwable
            )
        }
    }
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}