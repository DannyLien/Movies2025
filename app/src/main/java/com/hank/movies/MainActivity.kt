package com.hank.movies

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.hank.movies.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request

class MainActivity : AppCompatActivity() {
    private val TAG: String? = MainActivity::class.java.simpleName
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // recy
        val recy = binding.contentMain.recycler
        recy.setHasFixedSize(true)
        recy.layoutManager = LinearLayoutManager(this)
        // url json gson
        CoroutineScope(Dispatchers.IO).launch {
            val client = OkHttpClient()
            val request = Request.Builder()
                .url("https://api.themoviedb.org/3/movie/popular?language=zh-TW&page=1")
                .get()
                .addHeader("accept", "application/json")
                .addHeader(
                    "Authorization",
                    "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJlYzZmNDVlODJlODdiNTkzZWJjZDAxYWRmNWU2YWVjYiIsIm5iZiI6MTcxNDY0NjU3Ny4xNjMsInN1YiI6IjY2MzM2ZTMxODNlZTY3MDEyZDQwNjQ1NCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.PUpzJTeo0DfeszsXmp4WGhqyZjqaLVntqlfhqHbzra4"
                )
                .build()
            val response = client.newCall(request).execute()
            val json = response.body.string()
            val gson = Gson().fromJson(json, MoviesResult::class.java)
            runOnUiThread {   // recy
                recy.adapter = MoviesAdapter(gson.results)
            }
        }

        binding.fab.setOnClickListener { view ->
            finish()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                finish()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

}















