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

class QuotesScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quotes_screen)
        getQuote()
    }

    fun goToHomeScreen(view: View) {
        finish()
    }

    fun getQuote() {
        val quote = findViewById<TextView>(R.id.quote)
        val author = findViewById<TextView>(R.id.author)
        val spinner = findViewById<ProgressBar>(R.id.progressBarQuotes)

        val queue = Volley.newRequestQueue(this)
        val url = "https://quote-garden.herokuapp.com/quotes/random"
        val stringReuest = StringRequest(Request.Method.GET, url,
            Response.Listener { response ->

                val gsonBuilder = GsonBuilder()
                val gson = gsonBuilder.create()
                val joke = gson.fromJson(response, Quote::class.java)

                spinner.visibility = View.GONE

                quote.visibility = View.VISIBLE
                author.visibility = View.VISIBLE

                quote.setText(joke.quoteText)
                author.setText(joke.quoteAuthor)

            }, Response.ErrorListener { error ->
                spinner.visibility = View.GONE

                Toast.makeText(applicationContext, "Something went wrong", Toast.LENGTH_SHORT)
                    .show()
            })

        queue.add(stringReuest)
    }
    fun onClickRefresh(view: View) {
        val quote = findViewById<TextView>(R.id.quote)
        val author = findViewById<TextView>(R.id.author)
        val spinner = findViewById<ProgressBar>(R.id.progressBarQuotes)

        spinner.visibility = View.VISIBLE
        quote.visibility = View.GONE
        author.visibility = View.GONE
        getQuote()
    }
}
