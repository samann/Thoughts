package droidowl.thoughts;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.List;

@EActivity
public class ThoughtErrorsActivity extends AppCompatActivity {

    @ViewById(R.id.error_list_view)
    ListView mListView;

    ThoughtErrorAdapter mAdapter;

    List<ThoughtError> mErrors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thought_errors);
        mErrors = Utils.createErrorList();
        mAdapter = new ThoughtErrorAdapter(this, R.layout.error_list_item,
                mErrors);
        mListView.setAdapter(mAdapter);
    }
}
