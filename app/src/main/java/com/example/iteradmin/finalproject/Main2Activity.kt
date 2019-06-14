package com.example.iteradmin.finalproject

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject

class Main2Activity : AppCompatActivity() {

    private val user="123abcd"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val e=findViewById<EditText>(R.id.city)
        val b=findViewById<Button>(R.id.weather)
        val t=findViewById<TextView>(R.id.report)

        b.setOnClickListener {
            val str= e.text.toString()
            val url:String="https://api.openweathermap.org/data/2.5/weather?q=${str}&appid=3dd6d921ef1d85fe3390a6049027a14f"
            val queue=Volley.newRequestQueue(this)
            val jsonObjectRequest:JsonObjectRequest=
                    JsonObjectRequest(Request.Method.GET,url,null,Response.Listener {
                        response ->
                        val coord:JSONObject=response.getJSONObject("coord")
                        val weather:JSONArray=response.getJSONArray("weather")
                        val name:String=response.getString("name")

                        val report:String="Latitude: " + coord.get("lat") +
                                "longitude:" + coord.get("lon") +
                                "\n description: " + weather.getJSONObject(0).getString("description") +
                                "\n name: " + name
                        t.text=report
                    }, Response.ErrorListener {

                    })
        }
    }
}
