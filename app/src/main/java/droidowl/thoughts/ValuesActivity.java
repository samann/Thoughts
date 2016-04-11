package droidowl.thoughts;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.App;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by droidowl on 4/11/16.
 */
@EActivity(R.layout.activity_add_values)
public class ValuesActivity extends AppCompatActivity{

    @ViewById(R.id.add_value_fab)
    FloatingActionButton fab;

    @ViewById(R.id.values_list_view)
    ListView listView;

    @App
    ThoughtsApplication mApplication;

    ValuesAdapter mAdapter;

    List<ThoughtValue> mValues;

    @AfterViews
    void setup() {
        mValues = new ArrayList<>();
        mAdapter = new ValuesAdapter(this, R.layout.value_list_item, mValues);
        listView.setAdapter(mAdapter);
    }

    @Click(R.id.add_value_fab)
    void addWasTapped() {
        if (mValues != null) {
            mValues.add(new ThoughtValue("Hi", "7"));
            mAdapter.notifyDataSetChanged();
        }
    }
}
