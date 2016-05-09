package droidowl.thoughts;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;

import org.androidannotations.annotations.AfterPreferences;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.App;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.PreferenceByKey;
import org.androidannotations.annotations.PreferenceChange;
import org.androidannotations.annotations.PreferenceClick;
import org.androidannotations.annotations.PreferenceScreen;
import org.androidannotations.annotations.sharedpreferences.Pref;

/**
 * Created by Droidowl on 4/23/2016.
 */
@PreferenceScreen(R.xml.settings)
@EFragment
public class SettingsFragment extends PreferenceFragment {

    @Pref
    ThoughtsPreferences_ mPrefs;

    @PreferenceByKey(R.string.pref_notification_key)
    CheckBoxPreference notificationPref;

    @App
    ThoughtsApplication mApplication;


    @AfterPreferences
    void initPrefs() {
        notificationPref.setChecked(mPrefs.notificationsEnabled().getOr
                (false));
    }

    @AfterViews
    void setup() {
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("Settings");
    }

    @PreferenceChange(R.string.pref_notification_key)
    void notificationPrefChanged(boolean newValue, Preference preference) {
        mPrefs.notificationsEnabled().put(newValue);
        Toast.makeText(getActivity(), "settings changed to " + newValue, Toast
                .LENGTH_SHORT).show();
    }

    @PreferenceClick(R.string.pref_time_key)
    void notificationTimePrefChanged() {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View v = inflater.inflate(R.layout
                .notification_time_picker_view, null);
        Intent alarmIntent = new Intent(getActivity(),
                AlarmReceiver
                        .class);
        final PendingIntent pendingIntent = PendingIntent.getBroadcast
                (getActivity(), 0, alarmIntent, PendingIntent
                        .FLAG_UPDATE_CURRENT);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getString(R.string.time_title));
        builder.setView(v);
        builder.setPositiveButton(R.string.ok_button, new DialogInterface.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(DialogInterface dialog, int which) {
                TimePicker picker = (TimePicker) v.findViewById(R.id
                        .timePicker);
                int hour = picker.getHour();
                int minute = picker.getMinute();
                if (Utils.checkTime(hour, minute)) {
                    hour *= Utils.MILLI_IN_HOUR;
                    minute *= Utils.MILLI_IN_MINUTE;
                    mPrefs.notificationHour().put(hour);
                    mPrefs.notificationMinute().put(minute);
                    Toast.makeText(getActivity(), "Notification time " +
                            "updated: " + String.valueOf(hour / Utils.MILLI_IN_HOUR) +
                            ":" +
                            String.valueOf(minute / Utils.MILLI_IN_MINUTE),
                            Toast
                            .LENGTH_SHORT).show();
                }
                if (mPrefs.notificationsEnabled().get()) {
                    AlarmManager alarmManager = (AlarmManager)
                            getActivity().getSystemService(Context
                                    .ALARM_SERVICE);

                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
                            mPrefs.notificationHour().getOr(0)+
                                    mPrefs.notificationMinute()
                                    .getOr(0), Utils.MILLI_IN_DAY,
                            pendingIntent);
                } else {
                    Toast.makeText(getActivity(), "Please Enable Notifications"
                            , Toast.LENGTH_SHORT).show();
                }

            }
        });
        builder.create();
        builder.show();

    }

    @PreferenceClick(R.string.pref_clear_records_key)
    void clearRecordsClicked() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(mApplication.getString(R.string.confirm_remove));
        builder.setCancelable(false);
        builder.setPositiveButton(R.string.yes_button, new DialogInterface
                .OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mApplication.mFirebase.child("thoughts").removeValue();
            }
        });
        builder.setNegativeButton(R.string.cancel_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create();
        builder.show();
    }

    @PreferenceClick(R.string.pref_clear_values_key)
    void clearValuesClicked() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(mApplication.getString(R.string.confirm_remove));
        builder.setCancelable(false);
        builder.setPositiveButton(R.string.yes_button, new DialogInterface
                .OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mApplication.mFirebase.child("values").removeValue();
            }
        });
        builder.setNegativeButton(R.string.cancel_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create();
        builder.show();
    }
}
