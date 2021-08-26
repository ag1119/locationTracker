package com.learning.locationtracker.repo

import com.learning.locationtracker.LocationInfo
import retrofit2.Call
import retrofit2.Response

class LocationController(listener: EventListener) {
    interface EventListener{
        fun updateIpLocation(locationInfo: LocationInfo, lat: Double, lon: Double)
        fun showToast(msg: String)
    }
    private val statusSuccess: String = "success"
    private var eventListener: EventListener = listener

    fun fetchLocation(ip: String){
        val retrofit = RetrofitInstance.getRetroFitInstance()
        val service = retrofit.create(LocationService::class.java)
        val call = service.getLocationInfo(ip)
        call.enqueue(object :  retrofit2.Callback<LocationResp> {
            override fun onResponse(call: Call<LocationResp>?, response: Response<LocationResp>?) {
               if(response?.isSuccessful == true){
                   val locationResp = response.body()!!
                   if(locationResp.status == statusSuccess){
                       val location = locationResp.city + "," + " " + locationResp.region + " " + locationResp.zip
                       val locationInfo = LocationInfo(
                           locationResp.timeZone!!,
                           locationResp.isp!!,
                           ip,
                           location)
                       eventListener.updateIpLocation(locationInfo, locationResp.lat!!,
                           locationResp.lon!!)
                   }
                   else{
                       eventListener.showToast(locationResp.message!!)
                   }
               }
                else{
                   eventListener.showToast("something went wrong")
                }
            }

            override fun onFailure(call: Call<LocationResp>?, t: Throwable?) {
                println("error: $t")
            }

        })
    }
}
