package droidowl.thoughts;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.widget.Toast;

import org.androidannotations.annotations.AfterPreferences;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.PreferenceByKey;
import org.androidannotations.annotations.PreferenceChange;
import org.androidannotations.annotations.PreferenceScreen;

/**
 * Created by Droidowl on 4/23/2016.
 */
@PreferenceScreen(R.xml.settings)
@EActivity
public class ThoughtsSettingsActivity extends PreferenceActivity {

    @PreferenceByKey(R.string.pref_notification_key)
    CheckBoxPreference notificationPref;

    @AfterPreferences
    void setup() {
        notificationPref.setChecked(false);
    }

    @PreferenceChange(R.string.pref_notification_key)
    void notificationPrefChanged(boolean newValue, Preference preference) {

        Toast.makeText(this, "settings changed to " + newValue, Toast.LENGTH_SHORT).show();
    }
}
