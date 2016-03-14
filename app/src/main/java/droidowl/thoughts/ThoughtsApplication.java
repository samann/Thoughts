package droidowl.thoughts;

import android.app.Application;

import com.firebase.client.Firebase;

import org.androidannotations.annotations.EApplication;

/**
 * Created by droidowl on 2/29/16.
 */
@EApplication
public class ThoughtsApplication extends Application {

    Firebase mFirebase;

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(getApplicationContext());
        mFirebase = new Firebase("https://droidowlthoughts.firebaseio.com");
        mFirebase.keepSynced(true);
    }
}
