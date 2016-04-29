package droidowl.thoughts;

import android.support.v7.app.AppCompatActivity;

import com.mikepenz.materialdrawer.Drawer;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

/**
 * Created by droidowl on 4/27/16.
 */
@EActivity
public class BaseActivity extends AppCompatActivity {
    Drawer result;
    
    @AfterViews
    void setup() {

    }
}
