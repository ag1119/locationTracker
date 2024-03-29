package com.learning.locationtracker.repo.location

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface LocationService {
    @GET("json/{ip}")
    fun getLocationInfo(@Path("ip") ip: String) : Call<LocationResp>
}