package teamtreehouse.com.movienight.model;

import java.util.ArrayList;
import java.util.List;

public class MovieList {
    public List<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        MovieList.movieList = movieList;
    }

    public static List<Movie> movieList = new ArrayList<Movie>();

    public MovieList() {
    }
}
