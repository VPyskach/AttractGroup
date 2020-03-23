package com.test.attractgroup.api_services

interface HttpRequestCallback {
    fun onSuccess(res: String)

    fun onError(message: String)
}