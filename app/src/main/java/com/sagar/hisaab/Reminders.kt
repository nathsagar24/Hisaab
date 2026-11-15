package com.sagar.hisaab

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import org.json.JSONArray

object Reminders {
    private const val PREFS = "reminders"

    fun store(ctx: Context, json: String) {
        ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE).edit().putString("list", json).apply()
        schedule(ctx)
    }

    private fun pi(ctx: Context, id: String, title: String, text: String): PendingIntent {
        val i = Intent(ctx, ReminderReceiver::class.java)
            .putExtra("title", title).putExtra("text", text).putExtra("id", id.hashCode())
        return PendingIntent.getBroadcast(
            ctx, id.hashCode(), i, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
    }

    fun schedule(ctx: Context) {
        val sp = ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        val am = ctx.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        // cancel previously scheduled ones
        try {
            val oldIds = sp.getString("ids", "") ?: ""
            oldIds.split("|").filter { it.isNotEmpty() }.forEach { am.cancel(pi(ctx, it, "", "")) }
        } catch (e: Exception) {}
        val ids = StringBuilder()
        try {
            val arr = JSONArray(sp.getString("list", "[]"))
            val now = System.currentTimeMillis()
            for (k in 0 until arr.length()) {
                val o = arr.getJSONObject(k)
                val t = o.getLong("t")
                if (t <= now) continue
                val id = o.getString("id")
                am.setAndAllowWhileIdle(
                    AlarmManager.RTC_WAKEUP, t, pi(ctx, id, o.getString("title"), o.getString("text"))
                )
                if (ids.isNotEmpty()) ids.append("|")
                ids.append(id)
            }
        } catch (e: Exception) {}
        sp.edit().putString("ids", ids.toString()).apply()
    }
}
