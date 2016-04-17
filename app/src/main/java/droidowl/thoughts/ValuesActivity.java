package droidowl.thoughts;

import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
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
        Firebase valueBase = mApplication.mFirebase.child("value");
        valueBase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    ThoughtValue value = dataSnapshot.getValue(ThoughtValue.class);
                    mValues.add(value);
                    mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                ThoughtValue value = dataSnapshot.getValue(ThoughtValue.class);
                mValues.remove(value);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ThoughtValue value = mAdapter.getItem(position);
                mApplication.mFirebase.child("value").child(value.getKey())
                        .removeValue();
                return true;
            }
        });
    }

    @Click(R.id.add_value_fab)
    void addWasTapped() {
        if (mValues != null) {
            LayoutInflater inflater = (this).getLayoutInflater();
            final View v = inflater.inflate(R.layout.activity_create_value,
                    null);
            AlertDialog.Builder builder = new AlertDialog.Builder
                    (this);
            builder.setTitle("Add A Value");
            builder.setView(v);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    EditText titleText = (EditText) v.findViewById(R.id
                            .title_edittext);
                    EditText rankText = (EditText) v.findViewById(R.id
                            .rank_edittext);
                    Firebase ref = mApplication.mFirebase.child("value")
                            .push();
                    ThoughtValue value = new ThoughtValue(titleText.getText()
                            .toString(),
                            rankText.getText().toString(), ref.getKey());
                    ref.setValue(value);
                }
            });
            builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
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
