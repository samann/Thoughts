package droidowl.thoughts;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by droidowl on 2/29/16.
 */
public class ThoughtRecord implements Parcelable {
    String trigger;
    String beforeFeelings;
    String unhelpfulThoughts;
    String supportingFacts;
    String opposingFacts;
    String newPerspective;
    String outCome;
    String key;
    List<ThoughtError> thoughtErrors;

    double beforeRating;
    double afterRating;

    public ThoughtRecord() {
    }

    public ThoughtRecord(String trigger, String beforeFeelings, String unhelpfulThoughts, String supportingFacts, String opposingFacts, String newPerspective, String outCome) {
        this.trigger = trigger;
        this.beforeFeelings = beforeFeelings;
        this.unhelpfulThoughts = unhelpfulThoughts;
        this.supportingFacts = supportingFacts;
        this.opposingFacts = opposingFacts;
        this.newPerspective = newPerspective;
        this.outCome = outCome;
    }

    public ThoughtRecord(String trigger, String beforeFeelings, String unhelpfulThoughts, String supportingFacts, String opposingFacts, String newPerspective, String outCome, double beforeRating, double afterRating) {
        this.trigger = trigger;
        this.beforeFeelings = beforeFeelings;
        this.unhelpfulThoughts = unhelpfulThoughts;
        this.supportingFacts = supportingFacts;
        this.opposingFacts = opposingFacts;
        this.newPerspective = newPerspective;
        this.outCome = outCome;
        this.beforeRating = beforeRating;
        this.afterRating = afterRating;
    }

    public List<ThoughtError> getThoughtErrors() {
        return thoughtErrors;
    }

    public void setThoughtErrors(List<ThoughtError> thoughtErrors) {
        this.thoughtErrors = thoughtErrors;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTrigger() {
        return trigger;
    }

    public void setTrigger(String trigger) {
        this.trigger = trigger;
    }

    public String getBeforeFeelings() {
        return beforeFeelings;
    }

    public void setBeforeFeelings(String beforeFeelings) {
        this.beforeFeelings = beforeFeelings;
    }

    public String getUnhelpfulThoughts() {
        return unhelpfulThoughts;
    }

    public void setUnhelpfulThoughts(String unhelpfulThoughts) {
        this.unhelpfulThoughts = unhelpfulThoughts;
    }

    public String getSupportingFacts() {
        return supportingFacts;
    }

    public void setSupportingFacts(String supportingFacts) {
        this.supportingFacts = supportingFacts;
    }

    public String getOpposingFacts() {
        return opposingFacts;
    }

    public void setOpposingFacts(String opposingFacts) {
        this.opposingFacts = opposingFacts;
    }

    public String getNewPerspective() {
        return newPerspective;
    }

    public void setNewPerspective(String newPerspective) {
        this.newPerspective = newPerspective;
    }

    public String getOutCome() {
        return outCome;
    }

    public void setOutCome(String outCome) {
        this.outCome = outCome;
    }

    public double getBeforeRating() {
        return beforeRating;
    }

    public void setBeforeRating(double beforeRating) {
        this.beforeRating = beforeRating;
    }

    public double getAfterRating() {
        return afterRating;
    }

    public void setAfterRating(double afterRating) {
        this.afterRating = afterRating;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.trigger);
        dest.writeString(this.beforeFeelings);
        dest.writeString(this.unhelpfulThoughts);
        dest.writeString(this.supportingFacts);
        dest.writeString(this.opposingFacts);
        dest.writeString(this.newPerspective);
        dest.writeString(this.outCome);
        dest.writeString(this.key);
        dest.writeTypedList(thoughtErrors);
        dest.writeDouble(this.beforeRating);
        dest.writeDouble(this.afterRating);
    }

    protected ThoughtRecord(Parcel in) {
        this.trigger = in.readString();
        this.beforeFeelings = in.readString();
        this.unhelpfulThoughts = in.readString();
        this.supportingFacts = in.readString();
        this.opposingFacts = in.readString();
        this.newPerspective = in.readString();
        this.outCome = in.readString();
        this.key = in.readString();
        this.thoughtErrors = in.createTypedArrayList(ThoughtError.CREATOR);
        this.beforeRating = in.readDouble();
        this.afterRating = in.readDouble();
    }

    public static final Creator<ThoughtRecord> CREATOR = new Creator<ThoughtRecord>() {
        public ThoughtRecord createFromParcel(Parcel source) {
            return new ThoughtRecord(source);
        }

        public ThoughtRecord[] newArray(int size) {
            return new ThoughtRecord[size];
        }
    };
}
