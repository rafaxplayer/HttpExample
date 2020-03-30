package com.jrs.httpexample.io

import com.jrs.httpexample.models.ResponseUsers
import com.jrs.httpexample.models.User
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("?results=100&inc=id,email,name,picture,dob,phone")
    fun getUsers(): Call<ResponseUsers>


}