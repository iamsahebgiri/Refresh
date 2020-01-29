package com.example.refresh

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.GsonBuilder

class BoredScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bored_screen)
        getActivity()
    }

    fun onClickRefresh(view: View) {
        val spinner = findViewById<ProgressBar>(R.id.progressBarBored)
        val activity = findViewById<TextView>(R.id.activity)

        spinner.visibility = View.VISIBLE
        activity.visibility = View.GONE
        getActivity()
    }
    fun getActivity() {
        val activity = findViewById<TextView>(R.id.activity)
        val spinner = findViewById<ProgressBar>(R.id.progressBarBored)

        val queue = Volley.newRequestQueue(this)
        val url = "http://www.boredapi.com/api/activity/"
        val stringReuest = StringRequest(Request.Method.GET, url,
            Response.Listener { response ->

                val gsonBuilder = GsonBuilder()
                val gson = gsonBuilder.create()
                val act = gson.fromJson(response, Bored::class.java)

                spinner.visibility = View.GONE

                activity.visibility = View.VISIBLE

                activity.setText(act.activity)

            }, Response.ErrorListener { error ->
                spinner.visibility = View.GONE

                Toast.makeText(applicationContext, "Something went wrong", Toast.LENGTH_SHORT)
                    .show()
            })

        queue.add(stringReuest)
    }
    fun goToHomeScreen(view: View) {
        finish()
    }
}
