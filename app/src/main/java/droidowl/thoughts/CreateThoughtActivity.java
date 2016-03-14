package droidowl.thoughts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;

import com.firebase.client.Firebase;

import org.androidannotations.annotations.App;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@EActivity
public class CreateThoughtActivity extends AppCompatActivity {

    @App
    ThoughtsApplication mApplication;

    @ViewById(R.id.trigger_edit_text)
    EditText trigger;
    @ViewById(R.id.feelings_edit_text)
    EditText feelings;
    @ViewById(R.id.unhelpful_edit_text)
    EditText unhelpul;
    @ViewById(R.id.support_edit_text)
    EditText support;
    @ViewById(R.id.oppose_edit_text)
    EditText oppose;
    @ViewById(R.id.perspective_edit_text)
    EditText perspective;
    @ViewById(R.id.outcome_edit_text)
    EditText outcome;

    List<ThoughtError> selectedErrors;

    ThoughtRecord mRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_thought);
        selectedErrors = new ArrayList<>();
        if (getIntent().getExtras() != null) {
            mRecord = getIntent().getExtras().getParcelable("record");
            if (mRecord != null) {
                trigger.setText(mRecord.getTrigger());
                feelings.setText(mRecord.getBeforeFeelings());
                unhelpul.setText(mRecord.getUnhelpfulThoughts());
                support.setText(mRecord.getSupportingFacts());
                oppose.setText(mRecord.getOpposingFacts());
                perspective.setText(mRecord.getNewPerspective());
                outcome.setText(mRecord.getOutCome());
                selectedErrors = mRecord.getThoughtErrors();
            }

        }
    }

    @Click(R.id.done_button)
    void doneWasTapped() {
        ThoughtRecord record;
        if (mRecord == null) {
            record = new ThoughtRecord();
        } else {
            record = mRecord;
        }
            record.setTrigger(trigger.getText().toString());
            record.setBeforeFeelings(feelings.getText().toString());
            record.setUnhelpfulThoughts(unhelpul.getText().toString());
            record.setSupportingFacts(support.getText().toString());
            record.setOpposingFacts(oppose.getText().toString());
            record.setNewPerspective(perspective.getText().toString());
            record.setOutCome(outcome.getText().toString());
            record.setBeforeRating(0.0);
            record.setAfterRating(0.0);
            record.setThoughtErrors(selectedErrors);
        Calendar c = Calendar.getInstance();
        String date = c.get(Calendar.MONTH) + "/" + c.get(Calendar
                .DAY_OF_MONTH) + "/" + c.get(Calendar.YEAR);
        record.setDate(date);
        String time = c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE);
        record.setTime(time);
            Firebase ref = mApplication.mFirebase.child(Utils
                    .THOUGHT_RECORD_FIREBASE).push();
            record.setKey(ref.getKey());
            ref.setValue(record);
        finish();
    }

    @Click(R.id.error_button)
    void errorWasTapped() {
        Intent intent = new Intent(this, ThoughtErrorsActivity_.class);
        startActivityForResult(intent, 1);
    }

    @Click(R.id.cancel_button)
    void cancel() {
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && data != null) {
            Bundle extras = data.getExtras();
            selectedErrors = extras.getParcelableArrayList("selected");
            if (selectedErrors != null) {
                for (ThoughtError selectedError : selectedErrors) {
                    Log.e("tag", selectedError.getTitle());
                }
            }
        }
    }
}
