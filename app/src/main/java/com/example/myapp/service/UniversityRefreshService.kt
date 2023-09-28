package com.example.myapp.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.myapp.R
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class UniversityRefreshService : Service() {

    private val notificationId = 1
    private val channelId = "UniversityRefreshChannel"


    override fun onBind(p0: Intent?): IBinder? {
        return null
    }
    override fun onCreate() {
        super.onCreate()
        startForegroundService()
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {


     //   val myViewModel = (application as MyApplication).myViewModel

        GlobalScope.launch {
            while (true) {
                // Fetch data from the API and update the RecyclerView data here
          //   myViewModel.getUniversityData()

                // Delay for 10 seconds
                delay(10000)
            }
        }
        return START_STICKY
    }



    private fun startForegroundService() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel()
        }

        val notification = NotificationCompat.Builder(this, channelId)
            .setContentTitle("University Refresh Service")
            .setContentText("Refreshing university data")
            .setSmallIcon(R.mipmap.ic_launcher)
            .build()

        startForeground(notificationId, notification)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            channelId,
            "University Refresh Channel",
            NotificationManager.IMPORTANCE_DEFAULT
        )

        val notificationManager = getSystemService(NotificationManager::class.java)
        notificationManager?.createNotificationChannel(channel)
    }
}