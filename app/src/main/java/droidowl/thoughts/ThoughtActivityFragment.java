package droidowl.thoughts;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.App;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
@EFragment(R.layout.fragment_thought)
public class ThoughtActivityFragment extends Fragment {

    @ViewById(R.id.thoughts_list_view)
    ListView mListView;
    @App
    ThoughtsApplication mApplication;

    ThoughtAdapter mAdapter;
    List<ThoughtRecord> mRecords;


    public ThoughtActivityFragment() {
    }

    @AfterViews
    void setup() {
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("Thoughts");
        Firebase thoughtBaseRef = mApplication.mFirebase.child(Utils.THOUGHT_RECORD_FIREBASE);
        mRecords = new ArrayList<>();
        mAdapter = new ThoughtAdapter(getActivity(), R.layout.thought_list_item,
                mRecords);

        thoughtBaseRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ThoughtRecord record = dataSnapshot.getValue(ThoughtRecord
                        .class);
                mAdapter.add(record);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        mListView.setAdapter(mAdapter);

        listenForItemClick();

        listenForItemLongClick();
    }

    private void listenForItemLongClick() {
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ThoughtRecord record = mAdapter.getItem(position);
                mApplication.mFirebase.child(Utils.THOUGHT_RECORD_FIREBASE).child(record.getKey
                        ()).removeValue();
                mRecords.remove(position);
                mAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }

    private void listenForItemClick() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(),
                        ThoughtRecordActivity_.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable(Utils.THOUGHT_RECORD_EXTRAS,
                        mRecords.get(position));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Click(R.id.add_thought_fab)
    void createThoughtWasTapped() {
        Intent intent = new Intent(getActivity(), CreateThoughtActivity_.class);
        startActivity(intent);
    }
}
