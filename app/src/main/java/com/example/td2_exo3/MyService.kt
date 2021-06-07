package com.example.td2_exo3


import android.annotation.SuppressLint
import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.example.td2_exo3.App.Companion.CHANNEL_ID

import com.example.td2_exo3.notification.NotificationStructure
import com.example.td2_exo3.notification.NotificationUtils
import java.text.SimpleDateFormat
import java.util.*
import kotlin.concurrent.thread


class MyService : Service() {

    val TAG = "MyService"
    companion object{
        lateinit var mediaPlayer: MediaPlayer
    }

    private fun ShowLog(message: String) {
        Log.d(TAG, message)

    }

    override fun onBind(intent: Intent?): IBinder? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @SuppressLint("SimpleDateFormat")
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this,
            0, notificationIntent, 0)

        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(TAG)
            .setContentText(TAG)
            .setSmallIcon(R.drawable.ic_android)
            .setContentIntent(pendingIntent)
            .build()

        startForeground(1,notification)
        thread {


           while (true){
                val currentTime: Date = Calendar.getInstance().time
                val sdfDate= SimpleDateFormat("dd-MM-yyyy")
                val sdfTime=SimpleDateFormat("HH:mm")

                ShowLog(sdfTime.format(currentTime))


                val a=AzanTimes()



              //  if (sdfTime.format(currentTime) in a.times[sdfDate.format(currentTime)]!!){
                if (currentTime==currentTime){
                    val mNotificationUtils =
                        NotificationUtils(
                            this
                        )

                    val nb: Notification.Builder? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        mNotificationUtils.getAndroidChannelNotification(
                            NotificationStructure.title, NotificationStructure.body)
                    } else {
                        TODO("VERSION.SDK_INT < O")
                    }
                    mNotificationUtils.getManager().notify(101,nb!!.build())


                    mediaPlayer = MediaPlayer.create(this, R.raw.azan1)
                    mediaPlayer.start()

                    Thread.sleep(61000)
                }
           }

        }



        return START_NOT_STICKY

    }

    override fun onCreate() {
        super.onCreate()
    }

    override fun onDestroy() {
        super.onDestroy()
    }


}