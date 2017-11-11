package teamtreehouse.com.movienight;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import teamtreehouse.com.movienight.model.SortBy;
import teamtreehouse.com.movienight.model.Movie;
import teamtreehouse.com.movienight.model.TvShow;

public class DisplayMovies extends AppCompatActivity {
    Context context;
    @BindView(R.id.tvButton)
    Button tvButton;
    @BindView(R.id.moviesButton)
    Button moviesButton;

    public void alert() {
        AlertDialogFragment dialog = new AlertDialogFragment();
        dialog.show(getFragmentManager(), "Error");
    }

    private static final String TAG = DisplayMovies.class.getName();
    RecyclerView recyclerView;
    List<TvShow> tvShowList = new ArrayList<TvShow>();
    List<Movie> movieList = new ArrayList<Movie>();
    public static List<Movie> movies = new ArrayList<>();
    public static List<TvShow> shows = new ArrayList<>();
    Movie[] movieArray = new Movie[movieList.size()];
    List<String> movieStringList = new ArrayList<>();
    List<String> showStringList = new ArrayList<>();
    String[] newMovieArray = new String[movieList.size()];
    String[] newShowArray = new String[showStringList.size()];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_movies);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        if (extras.containsKey("tvListArray")) {
            context = this;
            shows = intent.getParcelableArrayListExtra("tvListArray");
            try {
                for (TvShow show : shows) {
                    showStringList.add(show.getShowTitle());
                }
            } catch (NullPointerException e) {
            }
            newShowArray = showStringList.toArray(newShowArray);
            Log.v(TAG, newShowArray.toString());
            recyclerView = (RecyclerView) findViewById(R.id.movieRecyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(new TvAdapter(this, newShowArray));
        }

        if (extras.containsKey("movieListArray")) {
            context = this;
            movies = intent.getParcelableArrayListExtra("movieListArray");
            try {
                for (Movie movie : movies) {
                    movieStringList.add(movie.getMovieTitle());
                }
            } catch (NullPointerException e) {
            }
            newMovieArray = movieStringList.toArray(newMovieArray);
            recyclerView = (RecyclerView) findViewById(R.id.movieRecyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(new Adapter(this, newMovieArray));
        }
        moviesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerView.setAdapter(new Adapter(context, newMovieArray));
            }
        });

        tvButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerView.setAdapter(new TvAdapter(context, newShowArray));
            }
        });

    }
}
