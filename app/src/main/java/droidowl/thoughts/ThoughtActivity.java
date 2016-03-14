package droidowl.thoughts;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

import org.androidannotations.annotations.App;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

@EActivity
public class ThoughtActivity extends AppCompatActivity {

    @ViewById(R.id.fab)
    FloatingActionButton createThoughtButton;
    @ViewById(R.id.thoughts_list_view)
    ListView mListView;
    @App
    ThoughtsApplication mApplication;
    ThoughtAdapter mAdapter;
    List<ThoughtRecord> mRecords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thought);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Firebase thoughtBaseRef = mApplication.mFirebase.child("thoughts");
        mRecords = new ArrayList<>();
        mAdapter = new ThoughtAdapter(this, R.layout.thought_list_item,
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

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),
                        ThoughtRecordActivity_.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable(Utils.THOUGHT_RECORD_EXTRAS,
                        mRecords.get(position));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ThoughtRecord record = mAdapter.getItem(position);
                mApplication.mFirebase.child("thoughts").child(record.getKey
                        ()).removeValue();
                mRecords.remove(position);
                mAdapter.notifyDataSetChanged();
                return true;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_thought, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_clear) {
            mApplication.mFirebase.child(Utils.THOUGHT_RECORD_FIREBASE)
                    .removeValue();
            mRecords.clear();
            mAdapter.notifyDataSetChanged();
        }

        return super.onOptionsItemSelected(item);
    }

    @Click(R.id.fab)
    void createThoughtWasTapped() {
        Intent intent = new Intent(this, CreateThoughtActivity_.class);
        startActivity(intent);
    }



    private void updateList() {
        Query query = mApplication.mFirebase.child
                (Utils.THOUGHT_RECORD_FIREBASE).orderByChild
                (Utils.THOUGHT_RECORD_FIREBASE);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mRecords.clear();
                for (DataSnapshot s : dataSnapshot.getChildren()) {
                    mRecords.add(s.getValue(ThoughtRecord.class));
                    mAdapter.addAll(mRecords);
                    mAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}
