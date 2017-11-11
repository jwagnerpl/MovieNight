package teamtreehouse.com.movienight.model;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import teamtreehouse.com.movienight.MainActivity;

public class Genres {

    public List<Genre> getGenreList() {
        return genreList;
    }

    public void setGenreList(List<Genre> genreList) {
        this.genreList = genreList;
    }

    private List<Genre> genreList = new ArrayList<Genre>();

    public class Genre {
        public String getGenreName() {
            return genreName;
        }

        public int getGenreId() {
            return genreId;
        }

        String genreName;
        int genreId;

        public Genre(String genreName, int genreId) {
            this.genreId = genreId;
            this.genreName = genreName;
        }
    }



    public Genres() {
        genreList.add(new Genre("Choose a Genre", 28));
        genreList.add(new Genre("Action", 28));
        genreList.add(new Genre("Adventure", 12));
        genreList.add(new Genre("Animation", 16));
        genreList.add(new Genre("Comedy", 35));
        genreList.add(new Genre("Crime", 80));
        genreList.add(new Genre("Documentary", 99));
        genreList.add(new Genre("Drama", 18));
        genreList.add(new Genre("Family", 10751));
        genreList.add(new Genre("Fantasy", 14));
        genreList.add(new Genre("History", 36));
        genreList.add(new Genre("Horror", 27));
        genreList.add(new Genre("Music", 10402));
        genreList.add(new Genre("Mystery", 9648));
        genreList.add(new Genre("Romance", 10749));
        genreList.add(new Genre("Science Fiction", 878));
        genreList.add(new Genre("TV Movie", 10770));
        genreList.add(new Genre("Thriller", 53));
        genreList.add(new Genre("War", 10752));
        genreList.add(new Genre("Western", 37));
    }

    public int getGenreNumber(String genreNamein){
        int genreNumber = 0;
        for(Genre genre:genreList){
            if(genreNamein == genre.genreName){
                genreNumber = genre.genreId;
            }
        }
        return genreNumber;
    }

    public String[] getGenreTitleArray() {
        return genreTitleArray;
    }

    String[] genreTitleArray = {"Choose a Genre", "Action", "Adventure", "Animation", "Comedy", "Crime", "Documentary", "Drama", "Family", "Fantasy", "History", "Horror"
            , "Music", "Mystery", "Romance", "Science Fiction", "TV Movie", "Thriller", "War", "Western"};


}
