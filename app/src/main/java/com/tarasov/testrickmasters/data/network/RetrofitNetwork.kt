package com.tarasov.testrickmasters.data.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


object RetrofitNetwork {

    val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("http://cars.cprogroup.ru/api/rubetek/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(okHttpClient)
        .build()
}