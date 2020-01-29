package com.example.refresh


import Joke
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
import kotlinx.android.synthetic.main.activity_jokes_screen.*

class JokesScreen : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jokes_screen)
        getJoke()
    }

    fun onClickRefresh(view: View) {
        val spinner = findViewById<ProgressBar>(R.id.progressBar)
        val setup = findViewById<TextView>(R.id.setup)
        val punchline = findViewById<TextView>(R.id.punchline)

        spinner.visibility = View.VISIBLE
        setup.visibility = View.GONE
        punchline.visibility = View.GONE
        getJoke()
    }

    fun getJoke() {
        val setup = findViewById<TextView>(R.id.setup)
        val punchline = findViewById<TextView>(R.id.punchline)
        val spinner = findViewById<ProgressBar>(R.id.progressBar)

        val queue = Volley.newRequestQueue(this)
        val url = "https://official-joke-api.appspot.com/random_joke"
        val stringReuest = StringRequest(Request.Method.GET, url,
            Response.Listener { response ->

                val gsonBuilder = GsonBuilder()
                val gson = gsonBuilder.create()
                val joke = gson.fromJson(response, Joke::class.java)

                spinner.visibility = View.GONE

                setup.visibility = View.VISIBLE
                punchline.visibility = View.VISIBLE

                setup.setText(joke.setup)
                punchline.setText(joke.punchline)

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