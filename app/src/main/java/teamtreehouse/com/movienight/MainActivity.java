package teamtreehouse.com.movienight;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import teamtreehouse.com.movienight.model.Genres;
import teamtreehouse.com.movienight.model.Movie;
import teamtreehouse.com.movienight.model.MovieList;
import teamtreehouse.com.movienight.model.TvList;
import teamtreehouse.com.movienight.model.TvShow;

public class MainActivity extends AppCompatActivity {
    String genreString;
    Genres genres = new Genres();
    private List<Movie> movieList;
    private List<TvShow> tvList;
    private String[] sortBySpinnerArray = {"Choose a Sorting Option:",
            "Revenue - Ascending", "Revenue - Descending",
            "Release Date - Ascending", "Release Date - Descending",
            "Popularity - Ascending", "Popularity - Descending",
            "Vote Count - Ascending", "Vote Count - Descending",
            "Average Vote - Ascending", "Average Vote - Descending"
    };
    private Movie movie;
    private static final String TAG = MainActivity.class.getName();
    private Request request;
    private OkHttpClient okHttpClient;

    public String getMovieURL(String fromDate, String toDate, String genre, int minRating, int minQuantityRatings, String sortBy) {

        String URL = "https://api.themoviedb.org/3/discover/movie?api_key=522918123e54a751b7b0cff5708774ac&language=en-US&include_adult=false&include_video=false&page=1&release_date.gte=" + fromDate + "&release_date.lte=" + toDate + "&vote_count.gte=" + minQuantityRatings + "&vote_average.gte=" + minRating + "&with_genres=" + genre + "&sort_by=" + sortBy;
        Log.v(TAG, URL);
        return URL;
    }

    public String getTvURL(String fromDate, String toDate, String genre, int minRating, int minQuantityRatings, String sortBy) {

        Log.i(TAG, "https://api.themoviedb.org/3/discover/tv?api_key=522918123e54a751b7b0cff5708774ac&language=en-US&sort_by=" + sortBy + "&first_air_date.gte=" + fromDate + "&first_air_date.lte=" + toDate + "&page=1&timezone=America%2FNew_York&vote_average.gte="
                + minRating + "&vote_count.gte=" + minQuantityRatings + "&with_genres=" + genre + "&include_null_first_air_dates=false");
        return "https://api.themoviedb.org/3/discover/tv?api_key=522918123e54a751b7b0cff5708774ac&language=en-US&sort_by=" + sortBy + "&first_air_date.gte=" + fromDate + "&first_air_date.lte=" + toDate + "&page=1&timezone=America%2FNew_York&vote_average.gte="
                + minRating + "&vote_count.gte=" + minQuantityRatings + "&with_genres=" + genre + "&include_null_first_air_dates=false";
    }

    @BindView(R.id.genresSelectedTextView)
    TextView genresSelectedTv;
    @BindView(R.id.ratingSeekBar)
    SeekBar ratingSeekBar;
    @BindView(R.id.ratingTextValue)
    TextView ratingTextValue;
    @BindView(R.id.minimumRatingsTextView)
    EditText ratingsQuantityValue;
    @BindView(R.id.tvCheckbox)
    CheckBox tvCheckbox;
    @BindView(R.id.movieCheckbox)
    CheckBox movieCheckbox;
    @BindView(R.id.genreSpinner)
    Spinner genreSpinner;
    @BindView(R.id.fromDate)
    TextView fromDate;
    @BindView(R.id.toDate)
    TextView toDate;
    @BindView(R.id.submit)
    Button submit;
    @BindView(R.id.sortBySpinner)
    Spinner sortBySpinner;

    Calendar currentDate;
    int day, month, year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        currentDate = Calendar.getInstance();
        day = currentDate.get(Calendar.DAY_OF_MONTH);
        month = currentDate.get(Calendar.MONTH);
        year = currentDate.get(Calendar.YEAR);

        month = month + 1;

        submit.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {


                String sortBy = sortBySpinner.getSelectedItem().toString();
                String sortByTv = "";
                String sortByMovies = "";

                if (sortBy == "Choose a Sorting Option:") {
                    sortByMovies = "revenue.asc";
                    sortByTv = "popularity.desc";
                }

                if (sortBy == "Revenue - Ascending") {
                    sortByMovies = "revenue.asc";
                }

                if (sortBy == "Revenue - Descending") {
                    sortByMovies = "revenue.desc";
                }

                if (sortBy == "Release Date - Ascending") {
                    sortByMovies = "release_date.asc";
                }

                if (sortBy == "Release Date - Descending") {
                    sortByMovies = "release_date.desc";
                }

                if (sortBy == "Popularity - Ascending") {
                    sortByMovies = "popularity.asc";
                }

                if (sortBy == "Popularity - Descending") {
                    sortByMovies = "popularity.desc";
                }

                if (sortBy == "Average Vote - Ascending") {
                    sortByMovies = "vote_average.asc";
                }

                if (sortBy == "Average Vote - Descending") {
                    sortByMovies = "vote_average.desc";
                }

                if (sortBy == "Vote Count - Ascending") {
                    sortByMovies = "vote_count.asc";
                }

                if (sortBy == "Vote Count - Descending") {
                    sortByMovies = "vote_count.desc";
                }

                if (sortBy == "Revenue - Ascending") {
                    sortByTv = "popularity.asc";
                }

                if (sortBy == "Revenue - Descending") {
                    sortByTv = "popularity.desc";
                }

                if (sortBy == "Release Date - Ascending") {
                    sortByTv = "first_air_date.asc";
                }

                if (sortBy == "Release Date - Descending") {
                    sortByTv = "first_air_date.desc";
                }

                if (sortBy == "Popularity - Ascending") {
                    sortByTv = "popularity.asc";
                }

                if (sortBy == "Popularity - Descending") {
                    sortByTv = "popularity.desc";
                }

                if (sortBy == "Average Vote - Ascending") {
                    sortByTv = "vote_average.asc";
                }

                if (sortBy == "Average Vote - Descending") {
                    sortByTv = "vote_average.desc";
                }

                if (sortBy == "Vote Count - Ascending") {
                    sortByTv = "vote_average.asc";
                }

                if (sortBy == "Vote Count - Descending") {
                    sortByTv = "vote_average.desc";
                }

                String genre = genreSpinner.getSelectedItem().toString();
                int minimumRating = ratingSeekBar.getProgress();
                int ratingQ = 0;
                try {
                    ratingQ = Integer.parseInt(ratingsQuantityValue.getText().toString());
                } catch (NumberFormatException e) {
                }

                Genres genres = new Genres();
                int genreId = 0;
                List<Genres.Genre> genreList = genres.getGenreList();
                for (Genres.Genre genreItem : genreList) {
                    if (genre == genreItem.getGenreName()) {
                        genreId = genreItem.getGenreId();
                    }
                }

                String fD = fromDate.getText().toString();
                String tD = toDate.getText().toString();

                if (fD.equals("")) {
                    fD = "1900-01-01";
                }
                if (tD.equals("")) {
                    tD = "2018-01-01";
                }


                okHttpClient = new OkHttpClient();


                if (tvCheckbox.isChecked() == false && movieCheckbox.isChecked() == false) {
                    Toast.makeText(getApplicationContext(), "Sorry, you must check an item to proceed.", Toast.LENGTH_LONG).show();
                }

                if (tvCheckbox.isChecked() == true && movieCheckbox.isChecked() == true) {

                    final Request tvRequest = new Request.Builder().url(getTvURL(fD, tD, genreString, minimumRating, ratingQ, sortByTv)).build();
                    Request movieRequest = new Request.Builder().url(getMovieURL(fD, tD, genreString, minimumRating, ratingQ, sortByMovies)).build();

                    okHttpClient.newCall(movieRequest).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Log.i(TAG, e.getMessage());
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            try {
                                String jsonData = response.body().string();
                                Log.v(TAG, jsonData);
                                if (response.isSuccessful()) {
                                    movieList = getMovieList(jsonData);

                                    okHttpClient.newCall(tvRequest).enqueue(new Callback() {
                                        @Override
                                        public void onFailure(Call call, IOException e) {
                                            Log.i(TAG, e.getMessage());
                                        }

                                        @Override
                                        public void onResponse(Call call, Response response) throws IOException {
                                            try {
                                                String jsonData = response.body().string();
                                                Log.v(TAG, jsonData);
                                                if (response.isSuccessful()) {
                                                    tvList = getTvList(jsonData);

                                                    Intent intent = new Intent(getApplicationContext(), DisplayMovies.class);
                                                    intent.putExtra("tvListArray", (Serializable) tvList);
                                                    intent.putExtra("movieListArray", (Serializable) movieList);
                                                    startActivity(intent);
                                                }
                                            } catch (IOException e) {
                                                Log.e(TAG, "Exception Caught: ", e);
                                            } catch (JSONException e) {
                                                Log.e(TAG, "Exception Caught: ", e);
                                            }
                                        }
                                    });

                                }
                            } catch (IOException e) {
                                Log.e(TAG, "Exception Caught: ", e);
                            } catch (JSONException e) {
                                Log.e(TAG, "Exception Caught: ", e);
                            }


                        }
                    });
                } else {

                    if (tvCheckbox.isChecked()) {
                        request = new Request.Builder().url(getTvURL(fD, tD, genreString, minimumRating, ratingQ, sortByTv)).build();
                        okHttpClient.newCall(request).enqueue(new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {
                                Log.i(TAG, e.getMessage());
                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                try {
                                    String jsonData = response.body().string();
                                    Log.v(TAG, jsonData);
                                    if (response.isSuccessful()) {
                                        List<TvShow> tvList = getTvList(jsonData);
                                        Intent intent = new Intent(getApplicationContext(), DisplayMovies.class);
                                        intent.putExtra("tvListArray", (Serializable) tvList);
                                        startActivity(intent);
                                    }
                                } catch (IOException e) {
                                    Log.e(TAG, "Exception Caught: ", e);
                                } catch (JSONException e) {
                                    Log.e(TAG, "Exception Caught: ", e);
                                }
                            }
                        });
                    }

                    if (movieCheckbox.isChecked()) {

                        request = new Request.Builder().url(getMovieURL(fD, tD, genreString, minimumRating, ratingQ, sortByMovies)).build();
                        okHttpClient.newCall(request).enqueue(new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {
                                Log.i(TAG, e.getMessage());
                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                try {
                                    String jsonData = response.body().string();
                                    Log.v(TAG, jsonData);
                                    if (response.isSuccessful()) {
                                        List<Movie> movieList = getMovieList(jsonData);
                                        Intent intent = new Intent(getApplicationContext(), DisplayMovies.class);
                                        intent.putExtra("movieListArray", (Serializable) movieList);
                                        startActivity(intent);
                                    }
                                } catch (IOException e) {
                                    Log.e(TAG, "Exception Caught: ", e);
                                } catch (JSONException e) {
                                    Log.e(TAG, "Exception Caught: ", e);
                                }
                            }
                        });
                    }
                }
            }
        });

        fromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        monthOfYear = monthOfYear + 1;
                        fromDate.setText(year + "-" + monthOfYear + "-" + dayOfMonth);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        toDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        monthOfYear = monthOfYear + 1;
                        toDate.setText(year + "-" + monthOfYear + "-" + dayOfMonth);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        Genres genres = new Genres();

        ArrayAdapter<String> categoriesAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, genres.getGenreTitleArray());
        genreSpinner.setAdapter(categoriesAdapter);


        ArrayAdapter<String> sortByAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, sortBySpinnerArray);
        sortBySpinner.setAdapter(sortByAdapter);
        Api api = new Api();
        api.getGenreUrl();

        ratingSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                ratingTextValue.setText(progressChangedValue + "");
            }
        });


    }

    private List<TvShow> getTvList(String jsonData) throws JSONException {

        JSONObject tvList = new JSONObject(jsonData);
        JSONArray tvArray = tvList.getJSONArray("results");
        int i;
        TvList allTvList = new TvList();

        for (i = 0; i < tvArray.length(); i++) {
            String title = tvArray.getJSONObject(i).getString("original_name");
            String overview = tvArray.getJSONObject(i).getString("overview");
            TvShow newShow = new TvShow(title, overview);
            TvList.tvList.add(newShow);
        }

        return TvList.tvList;

    }

    private List<Movie> getMovieList(String jsonData) throws JSONException {
        JSONObject movieList = new JSONObject(jsonData);
        JSONArray movieArray = movieList.getJSONArray("results");
        int i;
        MovieList allMovieList = new MovieList();

        for (i = 0; i < movieArray.length(); i++) {
            String title = movieArray.getJSONObject(i).getString("original_title");
            String overview = movieArray.getJSONObject(i).getString("overview");
            Movie newMovie = new Movie(title, overview);
            MovieList.movieList.add(newMovie);
        }

        return MovieList.movieList;
    }

    @Override
    protected void onStart() {
        super.onStart();

        genreSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (genreSpinner.getSelectedItem() != "Choose a Genre") {
                    genresSelectedTv.setText(genresSelectedTv.getText() + " " + genreSpinner.getSelectedItem().toString());

                    String genreNumber = genres.getGenreNumber(genreSpinner.getSelectedItem().toString()) + "";

                    if (genreString != null) {
                        genreString = genreString + "|" + genreNumber;
                        Log.i(TAG, genreString);
                    } else {
                        genreString = genreNumber;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });


    }


}
