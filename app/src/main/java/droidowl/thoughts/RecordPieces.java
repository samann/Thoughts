package droidowl.thoughts;

/**
 * Created by droidowl on 5/9/16.
 */
public class RecordPieces {
    String title;
    String body;

    public RecordPieces(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
