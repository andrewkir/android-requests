package com.example.andrewforwork.testrequests

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {
            val url = "/"
            val queue = Volley.newRequestQueue(this)
            val stringRequest = StringRequest(Request.Method.GET, url,
                    Response.Listener<String> { response ->
                        hellow.text =  "Response is: $response"
                    },
                    Response.ErrorListener { hellow.text =  "That didn't work!" })
            queue.add(stringRequest)
        }
        button2.setOnClickListener {
            val jsonBody = JSONObject()
            jsonBody.put("test","сясь")
            val requestBody = jsonBody.toString()
            val url = "h/api/sas"
            val req = object : StringRequest(Request.Method.POST, url,Response.Listener { resp ->
                Toast.makeText(this,"Succ, $resp",Toast.LENGTH_SHORT).show()
                hellow.text =  "Response is: $resp"
            }, Response.ErrorListener {
                Toast.makeText(this,"Nah",Toast.LENGTH_SHORT).show()
            }) {
                override fun getBodyContentType(): String {
                    return "application/json; charset=utf-8"
                }

                override fun getBody(): ByteArray {
                    return requestBody.toByteArray()
                }
            }

            Volley.newRequestQueue(this).add(req)
        }
        button3.setOnClickListener {
            val url = "/json"
            val req = JsonObjectRequest(Request.Method.GET, url,null,
                    Response.Listener { resp ->
                        hellow.text = resp["id"].toString()
                    },
                    Response.ErrorListener {
                        hellow.text =  "something went wrong"
                    })
            Volley.newRequestQueue(this).add(req)
        }
    }
}
