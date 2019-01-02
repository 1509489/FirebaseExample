package com.pixelarts.firebase_example

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.iid.FirebaseInstanceId
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener { task ->
                if (task.isSuccessful){
                    Log.d("FirebaseTAG", task.result?.token)
                }
            }

        val database = FirebaseDatabase.getInstance().getReference("example_table")
        database.setValue("Test value, Hello world")

        database.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                tvResults.text = dataSnapshot.getValue(String::class.java)
            }
        })

        btnLogEvent.setOnClickListener {
            val firebaseAnalytics = FirebaseAnalytics.getInstance(this)
            val param = Bundle()
            param.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "Button click analytic event log")
            firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, param)
            Log.d("FirebaseTAG", "log event button pressed")
        }
    }
}
