package com.hank.movies

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import com.google.android.material.snackbar.Snackbar
import com.hank.movies.databinding.RowMoviesViewBinding

class MoviesAdapter(val movies: List<Movie>) :
    RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {
    private val TAG: String? = MoviesAdapter::class.java.simpleName

    class MoviesViewHolder(var view: RowMoviesViewBinding) : ViewHolder(view.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val binding = RowMoviesViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MoviesViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movieData = movies.get(position)
        holder.view.movieTitle.text = movieData.title
        holder.view.moviePop.text = movieData.popularity.toString()
        // "https://image.tmdb.org/t/p/w500"+"/wwemzKWzjKYJFfCeiB57q3r4Bcm.png"
        holder.view.moviePoster.load(
            "https://image.tmdb.org/t/p/w500${movieData.poster_path}"
        ) {
            placeholder(R.drawable.rainbow).crossfade(2000) // 預設圖, 延2秒
            transformations(
//                CircleCropTransformation()      // : 圓型
                RoundedCornersTransformation(     // : 四周圓角
                    topLeft = 60f, topRight = 60f, bottomLeft = 60f, bottomRight = 60f
                )
            )
        }
        holder.itemView.setOnClickListener {
            Log.d(TAG, "onBindViewHolder: movie- ${movieData.title} , ${position} , ${movies.size}")
        }
    }

}












