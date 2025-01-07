package com.hank.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
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
        holder.view.moviePoster.setImageResource(R.drawable.rainbow)

    }

}
