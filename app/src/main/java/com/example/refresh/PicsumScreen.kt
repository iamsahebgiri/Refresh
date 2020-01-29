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

class PicsumScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picsum_screen)
        getRandomImage()
    }

    fun getRandomImage() {
        val picsum = findViewById<ImageView>(R.id.picsum)
        val spinner = findViewById<ProgressBar>(R.id.progressBarPicsum)

        val queue = Volley.newRequestQueue(this)
        val url = "https://meme-api.herokuapp.com/gimme"
        val stringReuest = StringRequest(Request.Method.GET, url,
            Response.Listener { response ->

                val gsonBuilder = GsonBuilder()
                val gson = gsonBuilder.create()
                val meme = gson.fromJson(response, Meme::class.java)

                Picasso.get().load(meme.url).into(picsum);
                spinner.visibility = View.GONE
                picsum.visibility = View.VISIBLE

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

    fun onClickRefresh(view: View) {
        val spinner = findViewById<ProgressBar>(R.id.progressBarPicsum)
        val picsum = findViewById<ImageView>(R.id.picsum)

        picsum.visibility = View.GONE
        spinner.visibility = View.VISIBLE
        getRandomImage()
    }

    fun onClickDownload(view: View) {}
}
