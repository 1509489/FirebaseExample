package com.pixelarts.firebase_example

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        super.onMessageReceived(remoteMessage)
        Log.d("FirebaseTAG", remoteMessage?.from)
    }

    override fun onNewToken(token: String?) {
        super.onNewToken(token)
        Log.d("FirebaseTAG", token)
    }
}
