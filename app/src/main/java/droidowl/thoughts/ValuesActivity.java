package droidowl.thoughts;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.App;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by droidowl on 4/11/16.
 */
@EActivity(R.layout.activity_add_values)
public class ValuesActivity extends AppCompatActivity{



    @ViewById(R.id.values_list_view)
    ListView mListView;

    @App
    ThoughtsApplication mApplication;

    ValuesAdapter mAdapter;

    List<ThoughtValue> mValues;

    @AfterViews
    void setup() {
        mValues = new ArrayList<>();
        mAdapter = new ValuesAdapter(this, R.layout.value_list_item, mValues);
        mListView.setAdapter(mAdapter);
        Firebase valueBase = mApplication.mFirebase.child(Utils.THOUGHT_VALUE_FIREBASE);
        valueBase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ThoughtValue value = dataSnapshot.getValue(ThoughtValue.class);
                mValues.add(value);
                mAdapter.notifyDataSetChanged();
                Collections.sort(mValues);
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

        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ThoughtValue value = mAdapter.getItem(position);
                mApplication.mFirebase.child(Utils.THOUGHT_VALUE_FIREBASE).child(value.getKey())
                        .removeValue();
                mValues.remove(value);
                mAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_value, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_clear_values) {
            mApplication.mFirebase.child(Utils.THOUGHT_VALUE_FIREBASE).removeValue();
            mValues.clear();
            mAdapter.notifyDataSetChanged();
        }
        if (id == R.id.action_set_alarm) {
            handleNotification();
        }
        return super.onOptionsItemSelected(item);
    }

    private void handleNotification() {
        Intent alarmIntent = new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 5000, pendingIntent);
    }

    @Click(R.id.add_value_fab)
    void addWasTapped() {
        if (mValues != null) {
            LayoutInflater inflater = (this).getLayoutInflater();
            final View v = inflater.inflate(R.layout.activity_create_value,
                    null);
            AlertDialog.Builder builder = new AlertDialog.Builder
                    (this);
            builder.setTitle(getString(R.string.add_value));
            builder.setView(v);
            builder.setPositiveButton(getString(R.string.ok_button), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    EditText titleText = (EditText) v.findViewById(R.id
                            .title_edittext);
                    EditText rankText = (EditText) v.findViewById(R.id
                            .rank_edittext);
                    Firebase ref = mApplication.mFirebase.child(Utils.THOUGHT_VALUE_FIREBASE)
                            .push();
                    ThoughtValue value = new ThoughtValue(titleText.getText()
                            .toString(),
                            Integer.parseInt(rankText.getText().toString()), ref
                    .getKey());
                    ref.setValue(value);
                }
            });
            builder.setNegativeButton(getString(R.string.cancel_button), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            builder.create();
            builder.show();
        }
    }
}
