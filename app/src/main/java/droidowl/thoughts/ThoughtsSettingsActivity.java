package droidowl.thoughts;

import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.util.Log;
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

    @PreferenceByKey(R.string.pref_time_key)
    EditTextPreference notificationTime;

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

    @PreferenceChange(R.string.pref_time_key)
    void notificationTimePrefChanged(String newValue, Preference preference) {
        try {
            String[] parts = newValue.split(":");
            int hour = Integer.parseInt(parts[0]);
            int min = Integer.parseInt(parts[1]);
            checkTime(hour, min);
        }catch (Exception e) {
            if (newValue.length() == 4) {
                try {
                    int hour = Integer.parseInt(newValue.substring(0,2));
                    int min = Integer.parseInt(newValue.substring(2));
                    checkTime(hour, min);
                } catch (Exception f) {
                    Log.e("Time error", newValue);
                    notificationTime.setText("8:00");
                }
            }
            Log.e("Time error", newValue);
            notificationTime.setText("8:00");
        }
    }

    private void checkTime(int hour, int min) {
        if (!(hour < 0 || hour > 24) && !(min < 0 || min >= 60)) {
            mPrefs.notificationHour().put(hour);
            mPrefs.notificationMinute().put(min);
            Toast.makeText(this, String.valueOf(hour) + ":" +  String.valueOf
                    (min),
                    Toast
                    .LENGTH_SHORT).show();
        }
    }
}
