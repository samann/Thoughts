package droidowl.thoughts;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity
public class ThoughtRecordActivity extends BaseActivity {

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

    @ViewById(R.id.errors_text_view)
    TextView errors;



    ThoughtRecord record;

    RecordRecyclerAdapter mAdapter;

    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_record);
        if (getIntent().getExtras() != null) {
            Bundle extras = getIntent().getExtras();
            record = extras.getParcelable(Utils.THOUGHT_RECORD_EXTRAS);
            if (record == null) {
                Log.d("TAG", "NULL");
            } else {
                mRecyclerView = (RecyclerView) findViewById(R.id
                        .record_items_recyclerview);
                mAdapter = new RecordRecyclerAdapter(Utils
                        .createPiecesFromRecords(record));

                LinearLayoutManager llm = new LinearLayoutManager(this);
                mRecyclerView.setAdapter(mAdapter);
                mRecyclerView.setLayoutManager(llm);

//                trigger.setText(record.getTrigger());
//                feelings.setText(record.getBeforeFeelings());
//                support.setText(record.getSupportingFacts());
//                oppose.setText(record.getOpposingFacts());
//                unhelpful.setText(record.getUnhelpfulThoughts());
//                perspective.setText(record.getNewPerspective());
//                outcome.setText(record.getOutCome());
//                String out = "";
//                if (record.getThoughtErrors() != null) {
//                    for (ThoughtError thoughtError : record.getThoughtErrors()) {
//                        out += thoughtError.getTitle() + "\n" + thoughtError
//                                .getDetail() + "\n\n";
//                    }
//                }
//                errors.setText(out);
            }
        }
    }
}
