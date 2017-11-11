package teamtreehouse.com.movienight.model;

public class SortBy {
    public static String[] sortByArray;

    public static String[] getSortByArray() {
        return sortByArray;
    }

    public SortBy() {
        sortByArray = new String[]{"popularity", "release date", "revenue", "average vote", "number of votes"};
    }
}
