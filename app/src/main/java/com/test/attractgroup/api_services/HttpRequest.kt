package com.test.attractgroup.api_services

import android.os.AsyncTask
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

class HttpRequest {

    fun makeSyncGetRequest(url: String, callback: HttpRequestCallback?) {
        val res = getRequest(url)
        if (res.isEmpty())
            callback?.onError("Response data is empty")
        else
            callback?.onSuccess(res)
    }

    fun makeAsyncGetRequest(url: String, callback: HttpRequestCallback?) {
        GetRequestTask(callback).execute(url)
    }


    private fun getRequest(url: String): String {

        val obj = URL(url)
        val connection = obj.openConnection() as HttpURLConnection

        connection.requestMethod = "GET"
        connection.setRequestProperty("User-Agent", "Mozilla/5.0")
        connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5")
        connection.setRequestProperty("Content-Type", "application/json")

        val bufferedReader = BufferedReader(InputStreamReader(connection.inputStream))
        val response = StringBuffer()
        var inputLine = bufferedReader.readLine()
        while (inputLine != null) {
            response.append(inputLine)
            inputLine = bufferedReader.readLine()
        }
        bufferedReader.close()

        return response.toString()
    }


    inner class GetRequestTask(val callback: HttpRequestCallback?) :
        AsyncTask<String, Void, String>() {

        override fun doInBackground(vararg params: String?): String {
            try {
                return getRequest(params[0] ?: "")
            }catch (e: Exception){
                return ""
            }
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            if (result == null || result.isEmpty())
                    callback?.onError("Response data is empty")
                else
                    callback?.onSuccess(result)
        }
    }
}