package droidowl.thoughts;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by droidowl on 4/11/16.
 */
public class ThoughtValue implements Comparable<ThoughtValue>,Parcelable {

    public static final Parcelable.Creator<ThoughtValue> CREATOR = new Parcelable.Creator<ThoughtValue>() {
        @Override
        public ThoughtValue createFromParcel(Parcel source) {
            return new ThoughtValue(source);
        }

        @Override
        public ThoughtValue[] newArray(int size) {
            return new ThoughtValue[size];
        }
    };
    String title;
    int rank;
    String key;

    public ThoughtValue(String title, int rank, String key) {
        this.title = title;
        this.rank = rank;
        this.key = key;
    }

    public ThoughtValue() { }

    protected ThoughtValue(Parcel in) {
        this.title = in.readString();
        this.rank = in.readInt();
        this.key = in.readString();
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeInt(this.rank);
        dest.writeString(this.key);
    }
}
