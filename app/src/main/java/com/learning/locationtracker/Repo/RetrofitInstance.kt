package com.learning.locationtracker.Repo

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

abstract class RetrofitInstance {
    companion object{
        val BASE_URL = "http://ip-api.com/";

        @Volatile
        private var retrofit: Retrofit? = null
        fun getRetroFitInstance() : Retrofit {
            if(retrofit == null){
               synchronized(this){
                   retrofit = Retrofit.Builder()
                       .baseUrl(BASE_URL)
                       .addConverterFactory(GsonConverterFactory.create())
                       .build()
               }
            }
            return retrofit!!
        }
    }
}