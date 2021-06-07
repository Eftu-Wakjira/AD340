package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.squareup.picasso.Picasso
import org.json.JSONException
import java.util.*


class TrafficAdapter : AppCompatActivity() {
    var cameraList: ListView? = null
    var listAdapter: CameraListAdapter? = null
    var dataUrl = TrafficCams.dataUrl
    var CamData : MutableList<TrafficCams> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_traffic)
        val actionbar = supportActionBar
        "Traffic List".also { actionbar!!.title = it }
        val displayHomeAsUpEnabled = actionbar?.setDisplayHomeAsUpEnabled(true)
        cameraList = findViewById(R.id.cameraList)
        listAdapter = CameraListAdapter(CamData)
        cameraList?.setAdapter(listAdapter)

        if (NetTools.hasNetworkConnection(this)){
            CamData(dataUrl)
        } else {
            Toast.makeText(this,"No Network Connection!",Toast.LENGTH_SHORT).show()

        }


    }

    inner class CameraListAdapter(
        private val values: List<TrafficCams>
    ) : ArrayAdapter<TrafficCams?>(
        applicationContext,
        0,
        values
    ) {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val inflater = context
                .getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val rowView = inflater.inflate(R.layout.list_row, parent, false)
            val description = rowView.findViewById<TextView>(R.id.description)
            val image = rowView.findViewById<ImageView>(R.id.image)
            val camera = values[position]
            description.text = camera.description
            val imageUrl = camera.imageUrl()
            if (!imageUrl.isEmpty()) {
                Picasso.get().load(imageUrl).into(image)
            }
            return rowView
        }
    }

    fun CamData(dataUrl: String?) {
        val queue = Volley.newRequestQueue(this)
        val cameraList: MutableList<TrafficCams> = ArrayList()

        val jsonReq = JsonObjectRequest(Request.Method.GET, TrafficCams.dataUrl, null, { response ->
            Log.d("CAMERAS", response.toString())
            try {
                val features = response.getJSONArray("Features")
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
                listAdapter!!.notifyDataSetChanged()
            } catch (e: JSONException) {
            }
        }) { error -> Log.d("JSON", "Error: " + error.message) }

        queue.add(jsonReq)
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true


    }
}
