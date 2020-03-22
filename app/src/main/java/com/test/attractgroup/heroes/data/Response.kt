package com.test.attractgroup.heroes.data

interface Response<T> {

    fun onSuccess(data: T)

    fun onError(message: String)
}