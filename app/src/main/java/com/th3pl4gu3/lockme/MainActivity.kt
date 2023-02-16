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
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.th3pl4gu3.lockme.ui.LockMeApp
import com.th3pl4gu3.lockme.ui.theme.LockMeTheme


class MainActivity : ComponentActivity() {

    companion object {
        private const val TAG = "MAIN_ACTIVITY_TAG"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        /**
         * Get the Accessibility Manager
         **/
        val manager = getSystemService(Context.ACCESSIBILITY_SERVICE) as AccessibilityManager

        /**
         * Verify if the user needs to see the UI
         * If the user doesn't need to see the UI, change the
         * activity theme
         **/
        if (!manager.isEnabled) {
            setTheme(android.R.style.Theme_NoTitleBar)
        }

        /**
         * Call the onCreate parent method
         **/
        super.onCreate(savedInstanceState)

        /**
         * Verify that Accessibility Services has been enabled
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

            finish()
        } else {
            Log.i("TESTING", "Setting content")
            setContent {
                LockMeTheme {
                    LockMeApp(
                        closeAppAction = { finish() },
                        openSettingsUiAction = { startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)); finish() }
                    )
                }
            }
        }
    }

    private fun getAccessibilityEventAnnouncement(): AccessibilityEvent =
        if (Build.VERSION.SDK_INT >= R) {
            AccessibilityEvent()
        } else {
            AccessibilityEvent.obtain()
        }
}

