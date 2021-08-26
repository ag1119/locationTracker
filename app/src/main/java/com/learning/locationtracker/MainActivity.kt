package com.learning.locationtracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.learning.locationtracker.repo.LocationController
import com.learning.locationtracker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnMapReadyCallback, LocationController.EventListener {
    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMainBinding
    private lateinit var ipEditText: EditText
    private lateinit var imageBtn: ImageView
    private lateinit var locationController: LocationController
    private lateinit var mapFragment: SupportMapFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
        initMap()

        imageBtn.setOnClickListener{
            val ip = ipEditText.text.toString()
            if(!TextUtils.isEmpty(ip)){
                locationController.fetchLocation(ip)
            }
        }
    }

    private fun initViews(){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        ipEditText = findViewById(R.id.ip_et)
        imageBtn = findViewById(R.id.image_btn)
        locationController = LocationController(this)
    }

    private fun initMap(){
        mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        locationController.fetchLocation("192.212.174.101")
    }

    override fun updateIpLocation(locationInfo: LocationInfo, lat: Double, lon: Double) {
        binding.locationInfo = locationInfo
        updateMarker(lat, lon, locationInfo.location)
    }

    override fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    private fun updateMarker(lat: Double, lon: Double, location: String){
        val coordinates = LatLng(lat,lon)
        mMap.addMarker(MarkerOptions().position(coordinates).title(location))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coordinates, 15F))
    }
}