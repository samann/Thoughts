package droidowl.thoughts;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_thought)
public class ThoughtActivity extends BaseActivity {



    Fragment mValuesActivityFragment;

    Fragment mThoughtActivityFragment;

    @AfterViews
    void setup() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mValuesActivityFragment = new ValuesActivityFragment_();
        mThoughtActivityFragment = new ThoughtActivityFragment_();
        final FragmentTransaction transaction = getFragmentManager()
                .beginTransaction();
        transaction.add(R.id.fragment_container,
                mThoughtActivityFragment);
        transaction.addToBackStack("thoughts").commit();
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
        result = new DrawerBuilder()
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
                                getFragmentManager().popBackStack();
                                result.closeDrawer();
                                break;
                            case 1:
                                break;
                            case 2:
                                if (!mValuesActivityFragment.isAdded()) {
                                    FragmentTransaction valuesTransation =
                                            getFragmentManager()
                                                    .beginTransaction();
                                    valuesTransation.replace(R.id
                                            .fragment_container,
                                                    mValuesActivityFragment);
                                    valuesTransation.addToBackStack
                                            ("values");
                                    valuesTransation.commit();
                                }
                                result.closeDrawer();
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
}
