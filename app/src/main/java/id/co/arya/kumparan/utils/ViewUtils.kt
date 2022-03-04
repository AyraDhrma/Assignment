package id.co.arya.kumparan.utils

import android.content.Context
import android.view.View
import android.widget.Toast

fun View.showView() {
    this.visibility = View.VISIBLE
}

fun View.hideView() {
    this.visibility = View.GONE
}

fun showToast(text: String, context: Context) {
    var toast: Toast? = null
    toast?.cancel()
    toast =
        Toast.makeText(
            context,
            "$text",
            Toast.LENGTH_SHORT
        )
    toast?.show()
}