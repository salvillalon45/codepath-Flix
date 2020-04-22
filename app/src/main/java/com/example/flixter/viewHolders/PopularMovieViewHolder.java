package com.example.flixter.viewHolders;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flixter.DetailActivity;
import com.example.flixter.R;
import com.example.flixter.glide.GlideApp;
import com.example.flixter.models.MovieModel;

import org.parceler.Parcels;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class PopularMovieViewHolder extends RecyclerView.ViewHolder {

//    FrameLayout container;
    RelativeLayout container;
    ImageView ivPoster;
    TextView tvTitle;
    View overlay;
    Context context;

    public PopularMovieViewHolder(@NonNull View itemView, Context context) {
        super(itemView);
        this.ivPoster = itemView.findViewById(R.id.ivPoster);
        this.container = itemView.findViewById(R.id.container);
        this.tvTitle = itemView.findViewById(R.id.tvTitle);
//        this.overlay = itemView.findViewById(R.id.overlay);
        this.context = context;
    }

    public void bind(final MovieModel movieModel) {
        String imageUrl = movieModel.getBackdropPath();

        // Code for Stretch Story 5
        // Here we are adding a rounded corners for the images using the Glide transformations.
        // I found the solution in this post: https://stackoverflow.com/questions/15142780/how-do-i-remove-extra-space-above-and-below-imagevie
        // Needed to include android:adjustViewBounds="true" in the imageView in popular_movie_view_holder
        int radius = 100; // corner radius, higher value = more rounded
        int margin = 10; // crop margin, set to 0 for corners with no crop
        GlideApp.with(context).load(imageUrl).transform(new RoundedCornersTransformation(radius, margin)).into(ivPoster);
//        Glide.with(context).load(imageUrl).into(ivPoster);

        // Code for Stretch Story 3
        // Here we are adding a play icon overlay to popular movies to indicate that the movie can be played
//        int opacity = 150; // from 0 to 255
//        int opacity = 10; // from 0 to 255
//        overlay.setBackgroundColor(opacity * 0x1000000); // black with a variable alpha
//        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.FILL_PARENT, 610);
//        params.gravity = Gravity.BOTTOM;
//        overlay.setLayoutParams(params);
//        overlay.invalidate(); // update the view

        // 1. Register click listener on the whole row
        container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 2. Navigate to a new activity on tap
                Intent intent = new Intent(context, DetailActivity.class);

                // Put information we want to send to the activity
                intent.putExtra("movie", Parcels.wrap(movieModel));
                intent.putExtra("isPopular", true);

                // Code for Stretch Story 1
                // Here we are implementing shared element transition. We decided to use TextView as the shared element
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context, tvTitle, "title_transition");
                context.startActivity(intent, options.toBundle());
            }
        });
    }

}
