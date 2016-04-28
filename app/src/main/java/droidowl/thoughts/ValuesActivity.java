package droidowl.thoughts;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

/**
 * Created by droidowl on 4/11/16.
 */
@EActivity(R.layout.activity_values)
public class ValuesActivity extends BaseActivity{

    @AfterViews
    void setup() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.values_toolbar);
        setSupportActionBar(toolbar);

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
                                break;
                            case 1:
                                break;
                            case 2:
                                Intent intent = new Intent(ValuesActivity
                                        .this,
                                        ValuesActivity_.class);
                                startActivity(intent);
                                break;
                            case 3:
                                Intent intent1 = new Intent(ValuesActivity
                                        .this, ThoughtsSettingsActivity_
                                        .class);
                                startActivity(intent1);
                        }
                        return true;
                    }
                })
                .build();

    }

}
