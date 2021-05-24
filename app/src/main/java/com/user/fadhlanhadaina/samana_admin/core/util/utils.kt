package com.user.fadhlanhadaina.samana_admin.core.util

import android.app.Activity
import android.content.Intent
import android.view.View

object utils {

    fun <A : Activity> Activity.startActivityAndFinish(activity: Class<A>) {
        Intent(this, activity).also {
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(it)
            finish()
        }
    }

    fun View.show(boolean: Boolean) {
        when(boolean) {
            true -> visibility = View.VISIBLE
            else -> visibility = View.GONE
        }
    }
}