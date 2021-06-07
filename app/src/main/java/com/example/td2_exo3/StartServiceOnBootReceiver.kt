package com.example.td2_exo3

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.content.ContextCompat


class StartServiceOnBootReceiver:BroadcastReceiver()
{
    override fun onReceive(context: Context?, intent: Intent?) {
        if(Intent.ACTION_BOOT_COMPLETED == intent!!.action){

            val serviceIntent = Intent(context, MyService::class.java)
            ContextCompat.startForegroundService(context!!, serviceIntent)

        }
    }

}



