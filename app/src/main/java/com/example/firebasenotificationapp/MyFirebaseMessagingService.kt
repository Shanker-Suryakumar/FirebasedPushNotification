package com.example.firebasenotificationapp

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.net.Uri
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import org.json.JSONException
import org.json.JSONObject


open class MyFirebaseMessagingService : FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        // Check if message contains a data payload.
        // Check if message contains a data payload.
        if (remoteMessage.data.isNotEmpty()) {
            Log.d(TAG, "Message data payload: " + remoteMessage.data)
            try {
                val data = JSONObject(remoteMessage.data as Map<*, *>)
                val jsonMessage = data.getString("extra_information")
                Log.d(TAG, """onMessageReceived: Extra Information: $jsonMessage""".trimIndent())
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }

        // Check if message contains a notification payload.
        if (remoteMessage.notification != null) {
            val title = remoteMessage.notification!!.title //get title
            val message = remoteMessage.notification!!.body //get message
            val clickAction = remoteMessage.notification!!.clickAction //get click_action
            Log.d(TAG, "Message Notification Title: $title")
            Log.d(TAG, "Message Notification Body: $message")
            Log.d(TAG, "Message Notification clickAction: $clickAction")
            sendNotification(title!!, message!!, clickAction!!)
        }
    }

    /**
     * Called if the FCM registration token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the
     * FCM registration token is initially generated so this is where you would retrieve the token.
     */
    override fun onNewToken(token: String) {
//        Log.d(TAG, "Refreshed token: $token")

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // FCM registration token to your app server.
//        sendRegistrationToServer(token)
    }

    private fun sendNotification(title: String, messageBody: String, clickAction: String) {
        val notificationIntent: Intent
        when (clickAction) {
            "MAIN_ACTIVITY" -> {
                notificationIntent = Intent(this, MainActivity::class.java)
                notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            }
            "SECOND_ACTIVITY" -> {
                notificationIntent = Intent(this, SecondActivity::class.java)
                notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            }
            else -> {
                notificationIntent = Intent(this, MainActivity::class.java)
                notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            }
        }

        val pendingIntent = PendingIntent.getActivity(
            this, 0 /* Request code */, notificationIntent,
            PendingIntent.FLAG_ONE_SHOT
        )

        val defaultSoundUri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val notificationBuilder: NotificationCompat.Builder = NotificationCompat.Builder(
            this, getString(
                R.string.default_notification_channel_id
            )
        )
            .setSmallIcon(R.drawable.ic_launcher)
            .setContentTitle(title)
            .setContentText(messageBody)
            .setAutoCancel(true)
            .setSound(defaultSoundUri)
            .setContentIntent(pendingIntent)

        val notificationManager =
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            } else {
                TODO("VERSION.SDK_INT < M")
            }
        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build())
    }

    override fun onDeletedMessages() {
        super.onDeletedMessages()
    }

    companion object {
        var TAG: String = MyFirebaseMessagingService::class.java.simpleName
    }

}