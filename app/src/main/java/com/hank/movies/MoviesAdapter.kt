package com.hank.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import coil.transform.RoundedCornersTransformation
import com.hank.movies.databinding.RowMoviesViewBinding

class MoviesAdapter(val movies: List<Movie>) :
    RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {
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
            placeholder(R.drawable.rainbow)
            transformations(RoundedCornersTransformation())
        }
    }

}
