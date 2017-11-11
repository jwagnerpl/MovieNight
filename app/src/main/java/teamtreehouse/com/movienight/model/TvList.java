package teamtreehouse.com.movienight.model;

import java.util.ArrayList;
import java.util.List;

public class TvList {

    public static List<TvShow> getTvList() {
        return tvList;
    }

    public static void setTvList(List<TvShow> tvList) {
        TvList.tvList = tvList;
    }

    public static List<TvShow> tvList = new ArrayList<>();

    public TvList() {
    }
}
