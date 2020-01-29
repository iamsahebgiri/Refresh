package com.example.refresh

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.GsonBuilder
import com.squareup.picasso.Picasso

class NamoScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_namo_screen)
    }

    fun goToHomeScreen(view: View) {
        finish()
    }

    fun getRandomNamoImage() {
        val namoImage = findViewById<ImageView>(R.id.namoImage)
        val spinner = findViewById<ProgressBar>(R.id.progressBarNamo)

        val queue = Volley.newRequestQueue(this)
        val url = "https://namo-memes.herokuapp.com/memes/1"
        val stringReuest = StringRequest(Request.Method.GET, url,
            Response.Listener { response ->

                val gsonBuilder = GsonBuilder()
                val gson = gsonBuilder.create()
                val meme:List<Namo> = gson.fromJson(response,Array<Namo>::class.java).toList()

                Picasso.get().load(meme[0].url).into(namoImage);
                spinner.visibility = View.GONE
                namoImage.visibility = View.VISIBLE

            }, Response.ErrorListener { error ->
                spinner.visibility = View.GONE

                Toast.makeText(applicationContext, "Something went wrong", Toast.LENGTH_SHORT)
                    .show()
            })

        queue.add(stringReuest)
    }
    fun onClickRefresh(view: View) {
        val namoImage = findViewById<ImageView>(R.id.namoImage)
        val spinner = findViewById<ProgressBar>(R.id.progressBarNamo)

        namoImage.visibility = View.GONE
        spinner.visibility = View.VISIBLE
        getRandomNamoImage()
    }
}
