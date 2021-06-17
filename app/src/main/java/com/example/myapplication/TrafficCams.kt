package com.example.myapplication

data class TrafficCams(val description: String, private val image: String, var type: String, var coords: DoubleArray ) {

    private val baseUrl: Map<String, String> = mapOf(
        "sdot" to "https://www.seattle.gov/trafficcams/images/",
        "wsdot" to "https://images.wsdot.wa.gov/nw/"
    )

    fun imageUrl() : String {
        return baseUrl[this.type] + this.image
    }

    companion object {
        var dataUrl = " https://web6.seattle.gov/Travelers/api/Map/Data?zoomId=13&type=2"



    }
}
