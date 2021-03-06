package com.user.fadhlanhadaina.core.util

import android.app.Activity
import android.content.Intent
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar

object Utils {

    fun <A : Activity> Activity.startActivityAndFinish(activity: Class<A>) {
        Intent(this, activity).also {
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(it).also {
                finish()
            }
        }
    }

    fun <A : Activity> Activity.startActivity(activity: Class<A>) {
        Intent(this, activity).also {
            startActivity(it)
        }
    }

    fun View.show(boolean: Boolean) {
        visibility = when(boolean) {
            true -> View.VISIBLE
            else -> View.GONE
        }
    }

    fun View.disable(boolean: Boolean) {
        isEnabled = when(boolean) {
            true -> false
            else -> true
        }
    }

    fun View.notifyFieldEmpty(activity: Activity) {
        requestFocus()
        computeScroll()
        activity.showAlertDialog("Error", "Tidak boleh ada field yang kosong!")
    }

    fun Activity.showSnackbar(view: View, charSequence: CharSequence, duration: Int) {
        Snackbar.make(view, charSequence, duration).show()
    }
    fun Activity.showAlertDialog(title: CharSequence?, charSequence: CharSequence?) {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(charSequence)
            .setNegativeButton("Close") { _, _ -> }
            .show()
    }
}