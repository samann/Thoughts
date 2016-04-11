package droidowl.thoughts;

import android.support.design.widget.FloatingActionButton;
import android.widget.ListView;

import org.androidannotations.annotations.App;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.List;

/**
 * Created by droidowl on 4/11/16.
 */
@EActivity
public class ValuesActivity {

    @ViewById(R.id.add_value_fab)
    FloatingActionButton fab;

    @ViewById(R.id.values_list_view)
    ListView listView;

    @App
    ThoughtsApplication mApplication;

    ValuesAdapter mAdapter;

    List<ThoughtValue> mValues;


}
