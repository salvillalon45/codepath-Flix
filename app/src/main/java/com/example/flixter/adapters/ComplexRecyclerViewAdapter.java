package com.example.flixter.adapters;

import android.content.Context;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flixter.R;
import com.example.flixter.models.MovieModel;
import com.example.flixter.viewHolders.NotPopularMovieViewHolder;
import com.example.flixter.viewHolders.PopularMovieViewHolder;

import java.util.List;

public class ComplexRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<MovieModel> movies;
    private final int POPULAR = 0;
    private final int NOT_POPULAR = 1;
    private final String TAG = "ComplexAdapter";

    public ComplexRecyclerViewAdapter(Context context, List<MovieModel> movies) {
        this.context = context;
        this.movies = movies;
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return movies.size();
    }

    // Helps identify the vote average for the movie
    @Override
    public int getItemViewType(int position) {
        Log.d(TAG, "Inside getItemViewType()");

        MovieModel movieModel = movies.get(position);
        Integer vote_average = movieModel.getVote_average();

        if (vote_average > 5) {
            Log.i(TAG, "Movie is Popular");
            Log.i(TAG, "Movie vote average above 5. The average is:: " + vote_average);
            return POPULAR;
        }
        else if (vote_average <= 5) {
            Log.i(TAG, "Movie is Not Popular");
            Log.i(TAG, "Movie vote average below 5. The average is:: " + vote_average);
            return NOT_POPULAR;
        }
        return -1;
    }

    // Usually involves inflating a layout from XML and returning the holder
    // This is a very expensive method since it is inflating the view
    // onCreateViewHolder should be call less times than onBindViewHolder
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "Inside onCreateViewHolder()");

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        RecyclerView.ViewHolder viewHolder;

        switch(viewType) {
            case POPULAR:
                Log.i(TAG, "Movie is Popular");
                View popularView = layoutInflater.inflate(R.layout.popular_movie_view_holder, parent, false);
                viewHolder = new PopularMovieViewHolder(popularView, context);
                break;
            case NOT_POPULAR:
                Log.i(TAG, "Movie is NOT Popular");
                View notPopularView = layoutInflater.inflate(R.layout.not_popular_movie_view_holder, parent, false);
                viewHolder = new NotPopularMovieViewHolder(notPopularView, context);
                break;
            default:
                Log.i(TAG, "Default");
                View normalView = layoutInflater.inflate(R.layout.item_movie, parent, false);
                viewHolder = new NotPopularMovieViewHolder(normalView, context);
                break;
        }
        return viewHolder;
    }

    // Involves populating data into the item through holder
    // This method is very cheap in comparison with onCreateViewHolder
    // We need to create as many view as they fit in the screen
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        Log.d(TAG, "Inside onBindViewHolder()");

        MovieModel movieModel = movies.get(position);

        switch(viewHolder.getItemViewType()) {
            case POPULAR:
                Log.i(TAG, "Movie is Popular");
                PopularMovieViewHolder popularView = (PopularMovieViewHolder) viewHolder;
                popularView.bind(movieModel);
                break;
            case NOT_POPULAR:
            default:
                Log.i(TAG, "Movie is Not Popular");
                NotPopularMovieViewHolder notPopularMovieViewHolder = (NotPopularMovieViewHolder) viewHolder;
                notPopularMovieViewHolder.bind(movieModel);
        }
    }
}
