package droidowl.thoughts;

import android.app.Fragment;
import android.app.FragmentTransaction;
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

    Fragment mSettingsFragment;

    @AfterViews
    void setup() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mValuesActivityFragment = new ValuesActivityFragment_();
        mThoughtActivityFragment = new ThoughtActivityFragment_();
        mSettingsFragment = new ThoughtsSettingsFragment_();

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
        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withName(R.string.app_name);
        SecondaryDrawerItem item2 = new SecondaryDrawerItem();
        item2.withName(R.string.action_value);
        result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withTranslucentStatusBar(true)
                .withCloseOnClick(true)
                .addDrawerItems(
                        new DividerDrawerItem(),
                        item1,
                        new DividerDrawerItem(),
                        item2,
                        new DividerDrawerItem(),
                        new SecondaryDrawerItem().withName(R.string.action_settings)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        switch (position) {
                            case 1:
                                if (!mThoughtActivityFragment.isAdded()) {
                                    getFragmentManager().popBackStack();
                                    getFragmentManager()
                                            .beginTransaction()
                                            .replace(R.id
                                                    .fragment_container,
                                                    mThoughtActivityFragment)
                                            .addToBackStack("thoughts")
                                            .commit();
                                }
                                result.closeDrawer();
                                break;
                            case 3:
                                if (!mValuesActivityFragment.isAdded()) {
                                    getFragmentManager().popBackStack();
                                    getFragmentManager()
                                                    .beginTransaction()
                                            .replace(R.id
                                                    .fragment_container,
                                                    mValuesActivityFragment)
                                            .addToBackStack("values")
                                            .commit();
                                }
                                result.closeDrawer();
                                break;
                            case 5:
                                if (!mSettingsFragment.isAdded()) {
                                    getFragmentManager().popBackStack();
                                    getFragmentManager()
                                            .beginTransaction().replace(R.id
                                                    .fragment_container,
                                            mSettingsFragment)
                                            .addToBackStack("settings")
                                            .commit();
                                }
                                result.closeDrawer();
                                break;
                        }
                        return true;
                    }
                })
                .build();
        result.getActionBarDrawerToggle().setDrawerIndicatorEnabled(true);
        result.setSelection(item1);
    }
}
