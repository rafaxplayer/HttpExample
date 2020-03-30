package com.jrs.httpexample.io

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiAdapter {

    companion object{
        private var API_SERVICE: ApiService? = null
        fun getApiService(): ApiService? {

            // Creamos un interceptor y le indicamos el log level a usar
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)

            // Asociamos el interceptor a las peticiones
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(logging)

            val baseUrl = "https://randomuser.me/api/"
            if (API_SERVICE == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build()) // <-- usamos el log level
                    .build()
                API_SERVICE = retrofit.create<ApiService>(ApiService::class.java)
            }
            return API_SERVICE
        }
    }



}