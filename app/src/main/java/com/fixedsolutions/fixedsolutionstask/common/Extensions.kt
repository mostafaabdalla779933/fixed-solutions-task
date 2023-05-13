package com.fixedsolutions.fixedsolutionstask.common


import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import retrofit2.HttpException
import java.net.UnknownHostException

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