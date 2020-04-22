package com.example.flixter.viewHolders;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flixter.DetailActivity;
import com.example.flixter.R;
import com.example.flixter.models.MovieModel;

import org.parceler.Parcels;

public class NotPopularMovieViewHolder extends RecyclerView.ViewHolder {

    RelativeLayout container;
    TextView tvTitle;
    TextView tvOverview;
    ImageView ivPoster;
    Context context;

    public NotPopularMovieViewHolder(@NonNull View itemView, Context context) {
        super(itemView);
        this.tvTitle = itemView.findViewById(R.id.tvTitle);
        this.tvOverview = itemView.findViewById(R.id.tvOverview);
        this.ivPoster = itemView.findViewById(R.id.ivPoster);
        this.container = itemView.findViewById(R.id.container);
        this.context = context;
    }

    public void bind(final MovieModel movieModel) {
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

        // 1. Register click listener on the whole row
        container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 2. Navigate to a new activity on tap
                Intent intent = new Intent(context, DetailActivity.class);

                // Put information we want to send to the activity
                intent.putExtra("movie", Parcels.wrap(movieModel));
                intent.putExtra("isPopular", false);

                // Code for Stretch Story 1
                // Here we are implementing shared element transition. We decided to use TextView as the shared element
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context, tvTitle, "title_transition");
                context.startActivity(intent, options.toBundle());
            }
        });
    }
}
