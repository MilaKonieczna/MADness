package com.example.madness.notifications

import android.Manifest
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.madness.R

class Send : BroadcastReceiver() {

    @SuppressLint("NewApi")
    override fun onReceive(context: Context, intent: Intent) {
//        val email = FirebaseAuth.getInstance().currentUser?.email.toString()
//        val firestore = FirebaseFirestore.getInstance()
//        firestore.collection("notifications")
//            .document(email)
//            .get()
//            .addOnSuccessListener { documentSnapshot ->
//                val selectedDateTime = documentSnapshot.getString("selected_date_time")
//                if (selectedDateTime != null) {
//                    val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
//                    val scheduledDateTime = sdf.parse(selectedDateTime)
//                    val currentDateTime = Calendar.getInstance().time
//
//                    if (currentDateTime >= scheduledDateTime) {
//                        sendNotification(context)
//                    }
//                }
//            }
//            .addOnFailureListener {
//            }

    }
    //TODO: fix notifications

    private fun sendNotification(context: Context) {
        val notificationBuilder = NotificationCompat.Builder(context, "ChannelId")
            .setSmallIcon(R.drawable.virus)
            .setContentTitle("Vaxx Alert")
            .setContentText("")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "ChannelId",
                "ChannelName",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val notificationManager =
                context.getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }

        val manager: NotificationManagerCompat = NotificationManagerCompat.from(context)
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            manager.notify(200, notificationBuilder.build())
        }
    }
}
