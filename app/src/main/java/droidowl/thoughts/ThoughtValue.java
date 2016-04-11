package droidowl.thoughts;

/**
 * Created by droidowl on 4/11/16.
 */
public class ThoughtValue {

    String title;
    String rank;

    public ThoughtValue(String title, String rank) {
        this.title = title;
        this.rank = rank;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
}
