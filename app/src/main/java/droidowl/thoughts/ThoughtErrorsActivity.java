package droidowl.thoughts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.androidannotations.annotations.App;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemLongClick;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

@EActivity
public class ThoughtErrorsActivity extends AppCompatActivity {

    @App
    ThoughtsApplication mApplication;

    @ViewById(R.id.error_list_view)
    ListView mListView;

    ThoughtErrorAdapter mAdapter;

    List<ThoughtError> mErrors;

    ArrayList<ThoughtError> selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thought_errors);
        mErrors = Utils.createErrorList();
        mAdapter = new ThoughtErrorAdapter(this, R.layout.error_list_item,
                mErrors);
        mListView.setAdapter(mAdapter);
        mListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mErrors.get(position).setChecked(true);
                mAdapter.notifyDataSetChanged();
            }
        });
        selected = new ArrayList<>();
    }

    @ItemLongClick(R.id.error_list_view)
    void itemLongClick(int pos) {
        ThoughtError thought = mAdapter.getItem(pos);
        thought.setChecked(true);
        selected.add(thought);
    }

    @Click(R.id.done_button)
    void doneWasTapped() {
        Intent intent = new Intent(this, ThoughtErrorsActivity_.class);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(Utils.THOUGHT_ERROR_EXTRAS, selected);
        intent.putExtras(bundle);
        setResult(1, intent);
        finish();
    }
}
