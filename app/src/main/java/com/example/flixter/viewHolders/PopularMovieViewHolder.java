package com.example.flixter.viewHolders;

import android.content.Context;
import android.content.res.Configuration;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flixter.R;
import com.example.flixter.models.MovieModel;

public class PopularMovieViewHolder extends RecyclerView.ViewHolder {

    ImageView ivPoster;
    Context context;

    public PopularMovieViewHolder(@NonNull View itemView, Context context) {
        super(itemView);
        this.ivPoster = itemView.findViewById(R.id.ivPoster);
        this.context = context;
    }

    public void bind(MovieModel movieModel) {
        String imageUrl = movieModel.getBackdropPath();
        Glide.with(context).load(imageUrl).into(ivPoster);
    }

}
