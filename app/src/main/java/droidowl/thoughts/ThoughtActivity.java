package droidowl.thoughts;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.App;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

@EActivity(R.layout.activity_thought)
public class ThoughtActivity extends AppCompatActivity {

    @ViewById(R.id.add_thought_fab)
    FloatingActionButton createThoughtButton;
    @ViewById(R.id.thoughts_list_view)
    ListView mListView;
    @App
    ThoughtsApplication mApplication;

    Fragment mValuesActivityFragment;

    Fragment mThoughtActivityFragment;

    ThoughtAdapter mAdapter;
    List<ThoughtRecord> mRecords;

    @AfterViews
    void setup() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Firebase thoughtBaseRef = mApplication.mFirebase.child(Utils.THOUGHT_RECORD_FIREBASE);
        mRecords = new ArrayList<>();
        mAdapter = new ThoughtAdapter(this, R.layout.thought_list_item,
                mRecords);
        mValuesActivityFragment = new ValuesActivityFragment_();
        mThoughtActivityFragment = new ThoughtActivityFragment_();
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
                Intent intent = new Intent(ThoughtActivity.this,
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
                mApplication.mFirebase.child(Utils.THOUGHT_RECORD_FIREBASE).child(record.getKey
                        ()).removeValue();
                mRecords.remove(position);
                mAdapter.notifyDataSetChanged();
                return true;
            }
        });
        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        } catch (NullPointerException e) {
            Log.e("NULL", "actionbar is null");
        }
        //if you want to update the items at a later time it is recommended to keep it in a variable
        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withName(R.string.app_name);
        SecondaryDrawerItem item2 = new SecondaryDrawerItem();
        item2.withName(R.string.action_value);
//create the drawer and remember the `Drawer` result object
        Drawer result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .addDrawerItems(
                        item1,
                        new DividerDrawerItem(),
                        item2,
                        new SecondaryDrawerItem().withName(R.string.action_settings)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        // do something with the clicked item :D
                        switch (position) {
                            case 0:
                                break;
                            case 1:
                                break;
                            case 2:
                                Intent intent = new Intent(ThoughtActivity
                                        .this,
                                        ValuesActivity_.class);
                                startActivity(intent);
                                break;
                            case 3:
                                Intent intent1 = new Intent(ThoughtActivity
                                        .this, ThoughtsSettingsActivity_
                                        .class);
                                startActivity(intent1);
                        }
                        return true;
                    }
                })
                .build();
        result.getActionBarDrawerToggle().setDrawerIndicatorEnabled(true);
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

        if (id == R.id.action_add_values) {
            Intent intent = new Intent(this, ValuesActivity_.class);
            startActivity(intent);
        }

        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, ThoughtsSettingsActivity_.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Click(R.id.add_thought_fab)
    void createThoughtWasTapped() {
        Intent intent = new Intent(this, CreateThoughtActivity_.class);
        startActivity(intent);
    }
}
