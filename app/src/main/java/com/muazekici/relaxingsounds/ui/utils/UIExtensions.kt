package com.muazekici.relaxingsounds.ui.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

inline fun <reified T : Activity> Context.startActivity(block: Intent.() -> Unit = {}) {
    val intent = Intent(this, T::class.java)
    block(intent)
    startActivity(intent)
}


fun ViewGroup.inflateLayout(layoutId: Int, isAdd: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, isAdd)
}

fun Int.toDp(context: Context): Int {
    return (this * context.resources.displayMetrics.density).toInt()
}

inline fun <T : Fragment> T.withArgs(argsBuilder: Bundle.() -> Unit): T =
    this.apply {
        arguments = Bundle().apply(argsBuilder)
    }

inline fun <T> LiveData<T>.filter(crossinline predicate: (T?) -> Boolean): LiveData<T> {
    val mutableLiveData: MediatorLiveData<T> = MediatorLiveData()
    mutableLiveData.addSource(this) {
        if (predicate(it))
            mutableLiveData.value = it
    }
    return mutableLiveData
}