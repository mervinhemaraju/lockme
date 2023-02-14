package com.th3pl4gu3.lockme

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Build.VERSION_CODES.R
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityManager
import android.widget.Toast
import androidx.activity.ComponentActivity


class MainActivity : ComponentActivity() {

    companion object {
        private const val TAG = "MAIN_ACTIVITY_TAG"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         * Get the Accessibility Manager
         **/
        val manager = getSystemService(Context.ACCESSIBILITY_SERVICE) as AccessibilityManager

        /**
         * Verify that it has been enabled
         **/
        if (manager.isEnabled) {

            // Log data
            Log.i(TAG, "Manager is enabled. Sending announcement event")

            /**
             *  Send a custom event to trigger the lock event
             **/
            with(getAccessibilityEventAnnouncement()) {
                this.eventType = AccessibilityEvent.TYPE_ANNOUNCEMENT
                this.packageName = applicationContext.packageName
                this.className = this@MainActivity.javaClass.name
                this.isEnabled = true
                manager.sendAccessibilityEvent(this)
            }
        }else{
            val intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)

            Toast.makeText(
                this,
                "You need to allow Lock me as a service in accessibility to continue.",
                Toast.LENGTH_LONG
            ).show()
        }
        finish()
    }

    private fun getAccessibilityEventAnnouncement(): AccessibilityEvent =
        if (Build.VERSION.SDK_INT >= R) {
            AccessibilityEvent()
        } else {
            AccessibilityEvent.obtain()
        }
}

