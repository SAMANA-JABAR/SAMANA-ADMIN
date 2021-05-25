package com.user.fadhlanhadaina.samana_admin.core.util

import android.app.Activity
import android.content.Intent
import android.view.View
import android.widget.Toast

object Utils {

    fun <A : Activity> Activity.startActivityAndFinish(activity: Class<A>) {
        Intent(this, activity).also {
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(it)
            finish()
        }
    }

    fun View.show(boolean: Boolean) {
        visibility = when(boolean) {
            true -> View.VISIBLE
            else -> View.GONE
        }
    }


    fun Activity.showToast(charSequence: CharSequence?, duration: Int) {
        Toast.makeText(this.applicationContext, charSequence, duration).show()
    }
}