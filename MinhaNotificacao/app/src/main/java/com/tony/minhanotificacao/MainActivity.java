package com.tony.minhanotificacao;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.core.app.NotificationCompat;

public class MainActivity extends Activity {

    Button btnNotifica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNotifica = findViewById(R.id.btnNotifica);

        btnNotifica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationCompat.Builder mBuilder =
                        new NotificationCompat.Builder(view.getContext())
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setContentTitle("Minha notificacao")
                                .setContentText("Olá, mundo!");

                Intent intent = new Intent(view.getContext(), SegundaActivity.class);

                TaskStackBuilder stackBuilder = TaskStackBuilder.create(view.getContext());
                stackBuilder.addParentStack(SegundaActivity.class);
                stackBuilder.addNextIntent(intent);

                PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

                mBuilder.setContentIntent(pendingIntent);


                NotificationManager mNotificationManager =
                        (NotificationManager) getSystemService(view.getContext().NOTIFICATION_SERVICE);

                mNotificationManager.notify(1, mBuilder.build());
            }
        });
    }
}