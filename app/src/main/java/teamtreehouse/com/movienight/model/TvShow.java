package teamtreehouse.com.movienight.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class TvShow implements Parcelable {

    public static List<teamtreehouse.com.movienight.model.Movie> allShows = new ArrayList<>();
    private String showTitle;
    private String showDescription;

    private TvShow(Parcel in) {
        showTitle = in.readString();
        showDescription = in.readString();
    }

    public static final Creator<teamtreehouse.com.movienight.model.TvShow> CREATOR = new Creator<teamtreehouse.com.movienight.model.TvShow>() {
        @Override
        public teamtreehouse.com.movienight.model.TvShow createFromParcel(Parcel in) {
            return new teamtreehouse.com.movienight.model.TvShow(in);
        }

        @Override
        public teamtreehouse.com.movienight.model.TvShow[] newArray(int size) {
            return new teamtreehouse.com.movienight.model.TvShow[size];
        }
    };

    public String getShowTitle() {
        return showTitle;
    }

    public void setShowTitle(String movieTitle) {
        this.showTitle = movieTitle;
    }

    public String getShowDescription() {
        return showDescription;
    }

    public void setShowDescription(String movieDescription) {
        this.showDescription = movieDescription;
    }

    public TvShow(String title, String description) {
        this.showTitle = title;
        this.showDescription = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(showTitle);
        parcel.writeString(showDescription);
    }

}

