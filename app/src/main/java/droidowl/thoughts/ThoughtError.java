package droidowl.thoughts;

/**
 * Created by droidowl on 3/1/16.
 */
public class ThoughtError {
    String title;
    String detail;

    public ThoughtError() {
    }

    public ThoughtError(String title, String detail) {
        this.title = title;
        this.detail = detail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
