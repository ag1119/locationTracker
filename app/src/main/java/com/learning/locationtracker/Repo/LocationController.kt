package com.learning.locationtracker.Repo

import com.learning.locationtracker.LocationInfo
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class LocationController(listener: EventListener) {
    interface EventListener{
        fun updateIpLocation(locationInfo: LocationInfo, lat: Double, lon: Double)
    }
    var eventListener: EventListener = listener

    fun fetchLocation(ip: String){
        val retrofit = RetrofitInstance.getRetroFitInstance()
        val service = retrofit.create(LocationService::class.java)
        val call = service.getLocationInfo(ip)
        call.enqueue(object :  retrofit2.Callback<LocationResp> {
            override fun onResponse(call: Call<LocationResp>?, response: Response<LocationResp>?) {
               if(response?.isSuccessful == true){
                   val locationResp = response.body()!!
                   val location = locationResp.city + "," + " " + locationResp.region + " " + locationResp.zip
                   val locationInfo = LocationInfo(
                       locationResp.timeZone!!,
                       locationResp.isp!!,
                       ip,
                       location)
                   eventListener.updateIpLocation(locationInfo, locationResp.lat!!,
                       locationResp.lon!!
                   );
               }
                else{

                }
            }

            override fun onFailure(call: Call<LocationResp>?, t: Throwable?) {
                System.out.println("error: " + t)
            }

        })
    }
}
