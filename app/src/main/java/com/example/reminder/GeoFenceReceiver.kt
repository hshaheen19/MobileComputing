package com.example.reminder

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofencingEvent

class GeoFenceReceiver : BroadcastReceiver()
{
    override fun onReceive(context: Context?, intent: Intent?) {

        val geofencingevent = GeofencingEvent.fromIntent(intent)
        val geofencingTransition = geofencingevent.geofenceTransition

        if(geofencingTransition == Geofence.GEOFENCE_TRANSITION_ENTER
            || geofencingTransition == Geofence.GEOFENCE_TRANSITION_DWELL)
        {
            var uid = intent!!.getIntExtra("uid",0)
            var text = intent.getStringExtra("message")

            MainActivity.showNotification(context!!,text)
        }
    }

}