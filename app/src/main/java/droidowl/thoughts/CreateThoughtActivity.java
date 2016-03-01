package droidowl.thoughts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import org.androidannotations.annotations.App;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_thought);
    }

    @Click(R.id.done_button)
    void doneWasTapped() {
        ThoughtRecord record = new ThoughtRecord();
        record.setTrigger(trigger.getText().toString());
        record.setBeforeFeelings(feelings.getText().toString());
        record.setUnhelpfulThoughts(unhelpul.getText().toString());
        record.setSupportingFacts(support.getText().toString());
        record.setOpposingFacts(oppose.getText().toString());
        record.setNewPerspective(perspective.getText().toString());
        record.setOutCome(outcome.getText().toString());
        record.setBeforeRating(0.0);
        record.setAfterRating(0.0);
        mApplication.mFirebase.child(Utils.THOUGHT_RECORD_FIREBASE).push().setValue(record);
        finish();
    }

    @Click(R.id.error_button)
    void errorWasTapped() {
        Intent intent = new Intent(this, ThoughtErrorsActivity_.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();

        }
    }
}
