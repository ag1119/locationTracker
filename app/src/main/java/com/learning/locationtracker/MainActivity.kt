package com.learning.locationtracker

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.learning.locationtracker.Repo.LocationController
import com.learning.locationtracker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnMapReadyCallback, LocationController.EventListener {
    private lateinit var mMap: GoogleMap
    lateinit var binding: ActivityMainBinding
    lateinit var ipEditText: EditText
    lateinit var imageBtn: ImageView
    lateinit var locationController: LocationController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        ipEditText = findViewById(R.id.ip_et)
        imageBtn = findViewById(R.id.image_btn)
        locationController = LocationController(this)
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        imageBtn.setOnClickListener{
            val ip = ipEditText.text.toString()
            if(!TextUtils.isEmpty(ip)){
                locationController.fetchLocation(ip)
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    override fun updateIpLocation(locationInfo: LocationInfo, lat: Double, lon: Double) {
        binding.locationInfo = locationInfo
        updateMarker(lat, lon)
    }
    fun updateMarker(lat: Double, lon: Double){
        val x = LatLng(lat,lon);
        mMap.addMarker(MarkerOptions().position(x).title("Marker in my location"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(x))
    }
}