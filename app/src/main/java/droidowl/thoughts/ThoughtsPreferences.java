package droidowl.thoughts;

import org.androidannotations.annotations.sharedpreferences.DefaultRes;
import org.androidannotations.annotations.sharedpreferences.SharedPref;

/**
 * Created by droidowl on 4/24/16.
 */
@SharedPref(SharedPref.Scope.UNIQUE)
public interface ThoughtsPreferences {

   @DefaultRes(R.bool.default_enable_notification)
   boolean notificationsEnabled();
}
