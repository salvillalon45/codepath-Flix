package com.example.flixter.viewHolders;

import android.content.Context;
import android.content.res.Configuration;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flixter.R;
import com.example.flixter.models.MovieModel;

public class NotPopularMovieViewHolder extends RecyclerView.ViewHolder {

    TextView tvTitle;
    TextView tvOverview;
    ImageView ivPoster;
    Context context;

    public NotPopularMovieViewHolder(@NonNull View itemView, Context context) {
        super(itemView);
        this.tvTitle = itemView.findViewById(R.id.tvTitle);
        this.tvOverview = itemView.findViewById(R.id.tvOverview);
        this.ivPoster = itemView.findViewById(R.id.ivPoster);
        this.context = context;
    }

    public void bind(MovieModel movieModel) {
        this.tvTitle.setText(movieModel.getTitle());
        this.tvOverview.setText(movieModel.getOverview());
        String imageUrl;

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
