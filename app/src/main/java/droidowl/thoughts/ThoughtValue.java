package droidowl.thoughts;

/**
 * Created by droidowl on 4/11/16.
 */
public class ThoughtValue implements Comparable<ThoughtValue>{

    String title;
    int rank;
    String key;

    public ThoughtValue(String title, int rank, String key) {
        this.title = title;
        this.rank = rank;
        this.key = key;
    }

    public ThoughtValue() { }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public int compareTo(ThoughtValue another) {
        return this.rank - another.getRank();
    }
}
