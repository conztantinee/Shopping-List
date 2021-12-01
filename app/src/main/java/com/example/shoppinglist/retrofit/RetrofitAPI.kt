package com.example.shoppinglist.retrofit


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitAPI {
    @GET("/latest")
    fun getMoney(@Query("from") base: String) : Call<Result>

    @GET("/{date}")
    fun getHistoric(@Path("date") date: String) : Call<Result>
}