# Flix
Flix is an app that allows users to browse movies from the [The Movie Database API](http://docs.themoviedb.apiary.io/#).
---

## Flix Part 2

### User Stories

#### REQUIRED (10pts)

- [x] (8pts) Expose details of movie (ratings using RatingBar, popularity, and synopsis) in a separate activity.
- [x] (2pts) Allow video posts to be played in full-screen using the YouTubePlayerView.

#### BONUS

- [x] Trailers for popular movies are played automatically when the movie is selected (1 point).
  - [x] When clicking on a popular movie (i.e. a movie voted for more than 5 stars) the video should be played immediately.
  - [x] Less popular videos rely on the detailed page should show an image preview that can initiate playing a YouTube video.
- [x] Add a play icon overlay to popular movies to indicate that the movie can be played (1 point).
- [x] Apply data binding for views to help remove boilerplate code. (1 point)
- [x] Add a rounded corners for the images using the Glide transformations. (1 point)

### App Walkthough GIF
<img src="walkthrough_v2.gif" width=250><br>

### Notes
Describe any challenges encountered while building the app.
- Challenges when implementing the Glide Transformation stretch story. I had to look at more resources in the internet as I had some troubles. For example, when attempting stretch story #5 
I tried using Glide for the image transformation but for some reason, it kept adding extra padding on top and below the image
I found the solution here: [Resource](https://stackoverflow.com/questions/15142780/how-do-i-remove-extra-space-above-and-below-imagevie) 
- Challenges when implementing the Glide Transformation stretch story. Another challenged that I encountered is that I noticed that 
Glide transformation take time to load. I noticed that some pictures had the Glide transformation applied but not others

## Flix Part 1

### User Stories
#### REQUIRED (10pts)
- [x] (10pts) User can view a list of movies (title, poster image, and overview) currently playing in theaters from the Movie Database API.

#### BONUS
- [x] (2pts) Views should be responsive for both landscape/portrait mode.
   - [x] (1pt) In portrait mode, the poster image, title, and movie overview is shown.
   - [x] (1pt) In landscape mode, the rotated alternate layout should use the backdrop image instead and show the title and movie overview to the right of it.

- [ ] (2pts) Display a nice default [placeholder graphic](https://guides.codepath.org/android/Displaying-Images-with-the-Glide-Library#advanced-usage) for each image during loading
- [x] (2pts) Improved the user interface by experimenting with styling and coloring.
- [x] (2pts) For popular movies (i.e. a movie voted for more than 5 stars), the full backdrop image is displayed. Otherwise, a poster image, the movie title, and overview is listed. Use Heterogenous RecyclerViews and use different ViewHolder layout files for popular movies and less popular ones.

### App Walkthough GIF
<img src="walkthrough_v1.gif" width=250><br>

### Notes
Describe any challenges encountered while building the app.
- Challenges faced in this project was understanding RecyclerView. I feel I still need to review more to get more comfortable on it.
- Another challenge was identifying where to get the number of votes from json. I decided to use the vote_average field.
Here is my understanding of my project so far for Part 1:
- MainActivity
    - In MainActivity, we initiate everything we will need to run the app
    - We get our recycle view by id
    - We create our adapter
    - We give the recycle view the adapter it will by setting to it
    - We also set layout manager
    - (Remember that the Adapter will be the one to put together the data that we get from the API and layout together)
    - We do our call to the API, give the date we receive to our movies list
    - And every time the data will change we will call notifyDataSetChanged()
- ComplexRecycleViewAdapter
    - We have some member variables for the class
    - We have the constructor that takes in the context and the array of movies that has data in it
    - We have some methods
        - getItemCount() just returns the size of the movies array
        - getItemViewType() helps identify the vote average for each movie
        - onCreateViewHolder() is the one that inflates a layout so that we are able to use. By inflating it we create a view. This function exactly says that it will create a view holder and in the code that is what we do! Really COOL!
        - onBindViewHolder() is the one that takes in this view holder and calls the bind function to give the view data to display to the user
    - THE IMPORTANT PART IS: These three functions will get call for every view that we need to create
- We also have two classes called PopularMovieViewHolder and NotPopularMovieViewHolder. These classes are just meant to represent one view for each movie that we have
- Remember that each view holder has it designated layout

### Open-source libraries used

- [Android Async HTTP](https://github.com/codepath/CPAsyncHttpClient) - Simple asynchronous HTTP requests with JSON parsing
- [Glide](https://github.com/bumptech/glide) - Image loading and caching library for Androids