package droidowl.thoughts;

import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.widget.Toast;

import org.androidannotations.annotations.AfterPreferences;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.PreferenceByKey;
import org.androidannotations.annotations.PreferenceChange;
import org.androidannotations.annotations.PreferenceScreen;
import org.androidannotations.annotations.sharedpreferences.Pref;

/**
 * Created by Droidowl on 4/23/2016.
 */
@PreferenceScreen(R.xml.settings)
@EActivity
public class ThoughtsSettingsActivity extends PreferenceActivity {

    @Pref
    ThoughtsPreferences_ mPrefs;

    @PreferenceByKey(R.string.pref_notification_key)
    CheckBoxPreference notificationPref;

    @AfterPreferences
    void setup() {
        notificationPref.setChecked(mPrefs.notificationsEnabled().getOr
                (false));
    }

    @PreferenceChange(R.string.pref_notification_key)
    void notificationPrefChanged(boolean newValue, Preference preference) {
        mPrefs.notificationsEnabled().put(newValue);
        Toast.makeText(this, "settings changed to " + newValue, Toast.LENGTH_SHORT).show();
    }
}
