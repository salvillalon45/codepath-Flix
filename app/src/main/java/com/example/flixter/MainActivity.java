package com.example.flixter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.util.Log;
import android.view.Window;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.flixter.adapters.ComplexRecyclerViewAdapter;
import com.example.flixter.adapters.MovieAdapter;
import com.example.flixter.databinding.ActivityMainBinding;
import com.example.flixter.models.MovieModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;

public class MainActivity extends AppCompatActivity {

    public static final String NOW_PLAYING_URL = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed ";
    public static final String TAG = "MAINACTIVITY";
    List<MovieModel> movies;
    ActivityMainBinding binding;
    RecyclerView rvMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // inside your activity (if you did not enable transitions in your theme)
//        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
//        // set an enter transition
//        getWindow().setEnterTransition(new Slide());
//        // set an exit transition
//        getWindow().setExitTransition(new Slide());
        super.onCreate(savedInstanceState);

        // Code for Stretch Story 4
        // Here we are implementing data binding for views to help remove boilerplate code
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        rvMovies = binding.rvMovies;
        movies = new ArrayList<>();

        // Create the adapter
        final MovieAdapter movieAdapter = new MovieAdapter(this, movies);
        final ComplexRecyclerViewAdapter complexRecyclerViewAdapter = new ComplexRecyclerViewAdapter(this, movies);

        // Set a Layout Manager on the recycler view
        rvMovies.setLayoutManager(new LinearLayoutManager(this));

        // Set the adapter on the recycler view
        rvMovies.setAdapter(complexRecyclerViewAdapter);

        // Create the network client to call api
        AsyncHttpClient client = new AsyncHttpClient();

        client.get(NOW_PLAYING_URL, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {
                Log.d(TAG, "onSuccess()");
                JSONObject jsonObject = json.jsonObject;

                try {
                    JSONArray results = jsonObject.getJSONArray("results");
                    Log.i(TAG, "Results:: " + results.toString());

                    // Modify the existing movies we had, we do not want to overwrite it
                    movies.addAll(MovieModel.fromJsonArray(results));

                    // Whenever the data changes we need to let the adapter so that it can render the recyclerview
//                    movieAdapter.notifyDataSetChanged();
                    complexRecyclerViewAdapter.notifyDataSetChanged();

                    Log.i(TAG, "Movies:: " + movies.size());
                }
                catch (JSONException e) {
                    Log.d(TAG, "Hit JSONException", e);
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
                Log.d(TAG, "onFailure()");
            }
        });
    }
}
