package droidowl.thoughts;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity
public class ThoughtRecordActivity extends AppCompatActivity {

    @ViewById(R.id.trigger_text_view)
    TextView trigger;

    @ViewById(R.id.feelings_text_view)
    TextView feelings;

    @ViewById(R.id.support_text_view)
    TextView support;

    @ViewById(R.id.oppose_text_view)
    TextView oppose;

    @ViewById(R.id.unhelpful_text_view)
    TextView unhelpful;

    @ViewById(R.id.perspective_text_view)
    TextView perspective;

    @ViewById(R.id.outcome_text_view)
    TextView outcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thought_record);
        if (getIntent().getExtras() != null) {
            Bundle extras = getIntent().getExtras();
            ThoughtRecord re = extras.getParcelable(Utils.THOUGHT_RECORD_EXTRAS);
            if (re == null) {
                Log.d("TAG", "NULL");
            } else {
                trigger.setText(re.getTrigger());
                feelings.setText(re.getBeforeFeelings());
                support.setText(re.getSupportingFacts());
                oppose.setText(re.getOpposingFacts());
                unhelpful.setText(re.getUnhelpfulThoughts());
                perspective.setText(re.getNewPerspective());
                outcome.setText(re.getOutCome());
            }
        }
    }

}
