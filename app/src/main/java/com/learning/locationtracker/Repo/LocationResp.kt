package com.learning.locationtracker.repo

import com.google.gson.annotations.SerializedName

class LocationResp {
    @SerializedName("status")
    var status: String? = null

    @SerializedName("message")
    var message: String? = null

    @SerializedName("lat")
    var lat: Double? = null

    @SerializedName("lon")
    var lon: Double? = null

    @SerializedName("timezone")
    var timeZone: String? = null

    @SerializedName("isp")
    var isp: String? = null

    @SerializedName("country")
    var country: String? = null

    @SerializedName("city")
    var city: String? = null

    @SerializedName("region")
    var region: String? = null

    @SerializedName("zip")
    var zip: String? = null

}
