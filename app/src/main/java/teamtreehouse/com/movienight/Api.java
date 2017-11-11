package teamtreehouse.com.movienight;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class Api {

    private static final String TAG = Api.class.getSimpleName();

    public static String getApiKey() {
        return apiKey;
    }

    List<String> genreArrayList = new ArrayList<>();

    private static final String apiKey = "522918123e54a751b7b0cff5708774ac";

    public String getGenreUrl() {
        return genreUrl;
    }

    private String genreUrl = "https://api.themoviedb.org/3/genre/movie/list?api_key=" + apiKey + "&language=en-US";
}






