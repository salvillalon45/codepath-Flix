package com.example.flixter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.flixter.adapters.ComplexRecyclerViewAdapter;
import com.example.flixter.adapters.MovieAdapter;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView rvMovies = findViewById(R.id.rvMovies);
        movies = new ArrayList<>();

        // Create the adapter
        final MovieAdapter movieAdapter = new MovieAdapter(this, movies);
        final ComplexRecyclerViewAdapter complexRecyclerViewAdapter = new ComplexRecyclerViewAdapter(this, movies);

        // Set the adapter on the recycler view
//        rvMovies.setAdapter(movieAdapter);
        rvMovies.setAdapter(complexRecyclerViewAdapter);

        // Set a Layout Manager on the recycler view
        rvMovies.setLayoutManager(new LinearLayoutManager(this));

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
