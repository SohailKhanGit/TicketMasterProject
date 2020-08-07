package com.example.ticketmasterproject.firebase

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.ticketmasterproject.MainActivity
import com.example.ticketmasterproject.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import java.lang.Exception

class FirebaseMessages: FirebaseMessagingService() {

    /*get and show messages as a notification
    *
    * */
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        try{
            showNotification(remoteMessage.notification?.title, remoteMessage.notification?.body)
        }catch(e:Exception){

        }
    }

    private fun showNotification(title: String?, body: String?) {
        //when user clicks notification, launch activity screen - pending intent
        //notification channels - from android 7 notification channels - configure custom notification

        val intent = Intent(this,MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT)

        val channelID = getString(R.string.channelID)
        val channelName = getString(R.string.channelName)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.O){
            setupNotification(channelID,channelName,notificationManager)
        }
        val notificationBuilder = NotificationCompat.Builder(this,channelID)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(title)
            .setContentText(body)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)

        notificationManager.notify(0,notificationBuilder.build())


    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private fun setupNotification(channelID: String, channelName: String, notificationManager: NotificationManager) {
        val channel = NotificationChannel(channelID,channelName,NotificationManager.IMPORTANCE_LOW)
        channel.enableVibration(true)
        channel.enableLights(true)
        channel.lightColor = Color.BLUE
        //channel.lockscreenVisibility
        notificationManager.createNotificationChannel(channel)


    }


    override fun onDeletedMessages() {
        super.onDeletedMessages()
    }



    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.i("Message","The refreshed token = ${token}")
    }
}