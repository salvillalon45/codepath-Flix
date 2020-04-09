package com.example.flixter.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flixter.R;
import com.example.flixter.models.MovieModel;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    Context context;
    List<MovieModel> movies;

    public MovieAdapter(Context context, List<MovieModel> movies) {
        this.context = context;
        this.movies = movies;
    }

    // Usually involves inflating a layout from XML and returning the holder
    // This is a very expensive method since it is inflating the view
    // onCreateViewHolder should be call less times than onBindViewHolder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("MovieAdapter", "onCreateViewHolder");
        View movieView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(movieView);
    }

    // Involves populating data into the item through holder
    // This method is very cheap in comparison with onCreateViewHolder
    // We need to create as many view as they fit in the screen
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("MovieAdapter", "onBindViewHolder:: " + position);
        // Get the movie at the passed at the position
        MovieModel movieModel = movies.get(position);

        // Bind the movie data into the view holder
        holder.bind(movieModel);
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return movies.size();
    }

    // ViewHolder is a representation of the row in the recycler view
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPoster;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvTitle = itemView.findViewById(R.id.tvTitle);
            this.tvOverview = itemView.findViewById(R.id.tvOverview);
            this.ivPoster = itemView.findViewById(R.id.ivPoster);
        }

        public void bind(MovieModel movieModel) {
            this.tvTitle.setText(movieModel.getTitle());
            this.tvOverview.setText(movieModel.getOverview());
            String imageUrl;
            System.out.println("What is title:: " + movieModel.getTitle());
            System.out.println("What is vote_average:: " + movieModel.getVote_average());
            // if phone is in landscape
            if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                // then imageUrl = back drop image
                imageUrl = movieModel.getBackdropPath();
            }
            else {
                // else imageUrl = poster image
                imageUrl = movieModel.getPosterPath();
            }

            Glide.with(context).load(imageUrl).into(ivPoster);
        }
    }
}
