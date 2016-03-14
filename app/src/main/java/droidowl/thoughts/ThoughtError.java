package droidowl.thoughts;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by droidowl on 3/1/16.
 */
public class ThoughtError implements Parcelable {
    String title;
    String detail;
    boolean checked;

    public ThoughtError() {
    }

    public ThoughtError(String title, String detail) {
        this.title = title;
        this.detail = detail;
    }

    public ThoughtError(String title, String detail, boolean checked) {
        this.title = title;
        this.detail = detail;
        this.checked = checked;
    }

    public String getTitle() {
        return title;
    }

      public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.detail);
        dest.writeByte(checked ? (byte) 1 : (byte) 0);
    }

    protected ThoughtError(Parcel in) {
        this.title = in.readString();
        this.detail = in.readString();
        this.checked = in.readByte() != 0;
    }

    public static final Parcelable.Creator<ThoughtError> CREATOR = new Parcelable.Creator<ThoughtError>() {
        public ThoughtError createFromParcel(Parcel source) {
            return new ThoughtError(source);
        }

        public ThoughtError[] newArray(int size) {
            return new ThoughtError[size];
        }
    };
}
