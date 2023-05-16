package com.fixedsolutions.fixedsolutionstask.common


import android.content.Context
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import retrofit2.HttpException
import java.net.UnknownHostException
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.util.concurrent.CountDownLatch

fun Fragment.showMessage(message:String){
    Toast.makeText(requireContext(),message, Toast.LENGTH_LONG).show()
}

fun EditText.isEmpty(): Boolean {
    return this.text.toString().trim().isEmpty()
}

fun EditText.getString(): String {
    return this.text.toString().trim()
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


inline fun <reified T : Any> Flow<T>.testFlowObserver(testScope: CoroutineScope): FlowTestObserver<T> {
    return FlowTestObserver<T>().also { observer ->
        testScope.launch {
            collect { value -> observer.onChanged(value) }
        }
    }
}


class FlowTestObserver<T> {
    private val latch = CountDownLatch(1)
    val values = mutableListOf<T>()

    fun onChanged(newValue: T) {
        values.add(newValue)
        latch.countDown()
    }

    suspend fun awaitValue(): T {
        if (values.isNotEmpty()) {
            return values.last()
        }

        latch.await()
        return values.last()
    }
}
