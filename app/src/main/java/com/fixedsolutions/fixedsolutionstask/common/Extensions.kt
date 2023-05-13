package com.fixedsolutions.fixedsolutionstask.common


import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import retrofit2.HttpException
import java.net.UnknownHostException
import androidx.swiperefreshlayout.widget.CircularProgressDrawable

fun Fragment.showMessage(message:String){
    Toast.makeText(requireContext(),message, Toast.LENGTH_LONG).show()
}

fun handleError(t: Throwable):String {
    Log.e("main", "handleError: ${t.localizedMessage}")
    t.printStackTrace()
    return when (t) {
        is HttpException -> {
            "Network Error"
        }
        is UnknownHostException -> {
            "No Internet"
        }
        else -> {
            "Something Went Wrong"
        }
    }
}

// get progressbar as drawable
fun Context.getLoadingDrawable(): CircularProgressDrawable {
    val circularProgressDrawable = CircularProgressDrawable(this)
    circularProgressDrawable.strokeWidth = 5f
    circularProgressDrawable.centerRadius = 30f
    circularProgressDrawable.start()
    return circularProgressDrawable
}
