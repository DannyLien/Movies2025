package com.hank.movies

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.hank.movies.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import kotlin.math.truncate

class MainActivity : AppCompatActivity() {
    private val TAG: String? = MainActivity::class.java.simpleName
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
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
//            gson.results.forEach {
//                Log.d(TAG, "TMDB: results: ${it.title}")
//            }
            runOnUiThread {   // recy
                recy.adapter = MoviesAdapter(gson.results)
            }
        }

        binding.fab.setOnClickListener { view ->
            finish()
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null)
//                .setAnchorView(R.id.fab).show()
        }

    }

//    fun setFinish(view: View) {
//        finish()
//    }

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