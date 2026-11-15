package com.sagar.hisaab

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.webkit.JavascriptInterface

class Bridge(private val ctx: Context) {
    @JavascriptInterface
    fun setReminders(json: String) { Reminders.store(ctx, json) }

    @JavascriptInterface
    fun openUrl(url: String) {
        try {
            ctx.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
        } catch (e: Exception) {}
    }
}
