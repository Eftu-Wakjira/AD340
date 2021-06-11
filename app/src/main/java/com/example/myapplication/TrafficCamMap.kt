package com.example.myapplication

import android.Manifest
import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import org.json.JSONException
import java.util.*

const val PERMISSION_REQUEST_LOCATION = 9
class TrafficCamMap : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var gMap: GoogleMap? = null
    private var CamData: MutableList<TrafficCams> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gmapcams)
        val actionbar = supportActionBar
        "Traffic Cameras Map".also { actionbar!!.title = it }
        val displayHomeAsUpEnabled = actionbar?.setDisplayHomeAsUpEnabled(true)


        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)


        val mapFragment = supportFragmentManager.findFragmentById(
            R.id.map_fragment
        ) as? SupportMapFragment
        mapFragment?.getMapAsync { googleMap ->

            gMap = googleMap
            Log.d("LOCATION", "has map")


            checkLocationPermission()


            CamData(TrafficCams.dataUrl)
        }

    }

    private fun checkLocationPermission() {
        Log.d("LOCATION", "checkPermission")
        if ( ContextCompat.checkSelfPermission(this@TrafficCamMap, ACCESS_COARSE_LOCATION) ==
            PackageManager.PERMISSION_GRANTED) {
            Log.d("LOCATION", "already granted")
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location : Location? ->
                    Log.d("LOCATION", location.toString())

                    if (location == null) {
                        val tmpLocation = Location(LocationManager.GPS_PROVIDER)
                        tmpLocation.latitude = 47.82395
                        tmpLocation.longitude = -122.29243
                        updateMap(tmpLocation)
                    }

                    updateMap(location)
                }

        } else {
            Log.d("LOCATION", "should request")
            if (ActivityCompat.shouldShowRequestPermissionRationale(this@TrafficCamMap,
                    ACCESS_COARSE_LOCATION)) {
                ActivityCompat.requestPermissions(this@TrafficCamMap,
                    arrayOf(ACCESS_COARSE_LOCATION), PERMISSION_REQUEST_LOCATION)
            } else {
                ActivityCompat.requestPermissions(this@TrafficCamMap,
                    arrayOf(ACCESS_COARSE_LOCATION), PERMISSION_REQUEST_LOCATION)
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == PERMISSION_REQUEST_LOCATION) {

            if (grantResults.size == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                Log.d("LOCATION", "granted")
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
                if (ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        this,
                        ACCESS_COARSE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                )
                fusedLocationClient.lastLocation
                    .addOnSuccessListener { location : Location? ->

                        Log.d("LOCATION2", location.toString())
                        updateMap(location)
                    }

            } else {

                Toast.makeText(this, "Access Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun CamData(dataUrl: String?) {
        val queue = Volley.newRequestQueue(this)
        val jsonReq = JsonObjectRequest(Request.Method.GET, dataUrl, null, { response ->
            Log.d("CAMERAS", response.toString())
            try {
                val features = response.getJSONArray("Features") // top-level node
                for (i in 1 until features.length()) {
                    val point = features.getJSONObject(i)
                    val pointCoords = point.getJSONArray("PointCoordinate")
                    val camera = point.getJSONArray("Cameras").getJSONObject(0)
                    val c = TrafficCams(
                        camera.getString("Description"),
                        camera.getString("ImageUrl"),
                        camera.getString("Type"),
                        doubleArrayOf(pointCoords.getDouble(0), pointCoords.getDouble(1))
                    )
                    CamData.add(c)
                }
                showMarkers()
            } catch (e: JSONException) {
            }
        }) { error -> Log.d("JSON", "Error: " + error.message) }

        queue.add(jsonReq)
    }

    private fun updateMap(location: Location?) {
        Log.d("LOCATION", "updateMap")
        if (location != null) {
            Log.d("LOCATION", "move map")
            gMap?.setMinZoomPreference(12f)
            gMap?.apply {
                val position = LatLng(location.latitude, location.longitude)
                addMarker(
                    MarkerOptions()
                        .position(position)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                        .title("My Location")
                )

                moveCamera(CameraUpdateFactory.newLatLng(position))

            }
        }
    }


    private fun showMarkers() {
        Log.d("LOCATION", "Show Markers")
        for (camera in CamData) {
            gMap?.apply {
                val position = LatLng(camera.coords[0], camera.coords[1])
                addMarker(
                    MarkerOptions()
                        .position(position)
                        .title(camera.description)
                )
            }
        }
    }

    private fun showToast(text: String?) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    override fun onMapReady(p0: GoogleMap) {
        TODO("Not yet implemented")
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true


    }
}