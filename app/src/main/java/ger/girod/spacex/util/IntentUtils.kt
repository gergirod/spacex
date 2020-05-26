package ger.girod.spacex.util

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo

object IntentUtils {

    fun isIntenteSafe(context: Activity?, intent: Intent): Boolean {
        val activities: List<ResolveInfo> = context?.packageManager!!.queryIntentActivities(
            intent,
            PackageManager.MATCH_DEFAULT_ONLY
        )
        return activities.isNotEmpty()
    }

}