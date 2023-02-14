package com.th3pl4gu3.lockme

import android.accessibilityservice.AccessibilityService
import android.util.Log
import android.view.accessibility.AccessibilityEvent

/**
 * Custom Accessibility Service
 **/
class MyAccessibilityService : AccessibilityService() {

    companion object {
        private const val TAG = "MY_ACCESSIBILITY_SERVICE_TAG"
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent) {
        Log.i(TAG, "Event change triggered: ${event.eventType} & ${event.packageName}")
        Log.i(TAG, "Current data: ${AccessibilityEvent.TYPE_ANNOUNCEMENT} & ${applicationContext.packageName}")

        if (
            event.eventType == AccessibilityEvent.TYPE_ANNOUNCEMENT &&
            event.packageName == applicationContext.packageName
        ) {
            Log.i(TAG, "Locking device...")
            performLock()
        }
    }

    override fun onServiceConnected() {
        super.onServiceConnected()
        Log.i("EVENT_CHANGE_HIT", "Service connected")
    }

    override fun onInterrupt() {
        Log.i("EVENT_CHANGE_HIT", "Service interrupted")
    }

    private fun performLock() {
        performGlobalAction(GLOBAL_ACTION_LOCK_SCREEN)
    }
}
