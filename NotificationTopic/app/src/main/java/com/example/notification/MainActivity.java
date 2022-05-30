package com.example.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button notification_btn;
    private String ID_CHANNEL;
    private int ID_NOTFICATION;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViews();
        /**
         * Create Channel
         */
        createNotificationChannel();
        btnOnclickListner();
    }

    private void initializeViews() {
        notification_btn = (Button) findViewById(R.id.notificaiton);
        ID_CHANNEL = getString(R.string.CHANNEL_ID);
        ID_NOTFICATION = getResources().getInteger(R.integer.NOTIFICAITON_ID);
    }

    private void btnOnclickListner() {
        notification_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNotification();
            }
        });
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {

            CharSequence name = getString(R.string.CHANNEL_NAME);
            String description = getString(R.string.CHANNEL_DESCRIPTION);

            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel = new NotificationChannel(ID_CHANNEL, name, importance);
            channel.setDescription(description);
            /** Register the channel with the system; you can't change the importance
             * or other notification behaviors after this
             */
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void createNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, ID_CHANNEL)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(getString(R.string.CHANNEL_NAME))
                .setContentText(getString(R.string.CHANNEL_DESCRIPTION))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(false);
        /**
         * Build Notification
         */
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        /**
         *  IMPORTANT
         * NOTE:-
         *  notificationId is a unique int for each notification that you must define
         *  For Checking use below Code to generate different notificaitons
         * notificationManager.notify(ID_NOTFICATION++, builder.build());
         */
        notificationManager.notify(ID_NOTFICATION, builder.build());
    }


}