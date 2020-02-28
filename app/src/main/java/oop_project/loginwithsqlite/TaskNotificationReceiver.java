package oop_project.loginwithsqlite;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.widget.Toast;

/**
 * Created by Sumon on 8/4/2017.
 */

public class TaskNotificationReceiver extends BroadcastReceiver {

    private Notification notification;
    private NotificationManager nManager;


    @Override
    public void onReceive(Context context, Intent intent) {
        String taskName = intent.getStringExtra("task_name");

        Toast.makeText(context, "You need to do your task namely: " + taskName, Toast.LENGTH_SHORT).show();

        Vibrator taskVibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);

        taskVibrator.vibrate(5000);


    }


    private void notifyUser(String taskName, Notification n) {

        //  n = NotificationCompat TODO


    }
}
