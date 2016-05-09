package droidowl.thoughts;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import org.androidannotations.annotations.EReceiver;
import org.androidannotations.annotations.sharedpreferences.Pref;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by droidowl on 4/21/16.
 */
@EReceiver
public class AlarmReceiver extends BroadcastReceiver {

    @Pref
    ThoughtsPreferences_ mPrefs;

    @Override
    public void onReceive(Context context, Intent intent) {
        Calendar now = GregorianCalendar.getInstance();
        int hourNow = now.get(Calendar.HOUR) * 3600000;
        int minuteNow = now.get(Calendar.MINUTE) * 60000;
        if(mPrefs.notificationsEnabled().get() &&
                hourNow == mPrefs.notificationHour().get() &&
                minuteNow == mPrefs.notificationMinute().get()) {
            Log.e("TIME TO SEND", String.valueOf(hourNow) + ":" + String
                    .valueOf
                    (minuteNow));
            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(context)
                            .setContentTitle(context.getString(R.string.notification_title))
                            .setSmallIcon(R.drawable.thought_cloud_jon_phill_02r)
                            .setContentText(context.getString(R.string.notification_text));
            Intent resultIntent = new Intent(context, MainActivity_.class);
            TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
            stackBuilder.addParentStack(MainActivity_.class);
            stackBuilder.addNextIntent(resultIntent);
            PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
            mBuilder.setContentIntent(resultPendingIntent);
            NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            mNotificationManager.notify(1, mBuilder.build());
        }
        Log.e("TIME NOT TO SEND", String.valueOf(hourNow) + ":" + String
                .valueOf
                (minuteNow));
    }
}
