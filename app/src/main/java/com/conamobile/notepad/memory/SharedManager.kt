package com.conamobile.notepad.memory

import android.content.Context

class SharedManager(context: Context) {
    private val pref = context.getSharedPreferences("my_shared", Context.MODE_PRIVATE)

    fun isSavedManager(isSavedManager: Boolean) {
        val editor = pref.edit()
        editor.putBoolean("isSavedManager", isSavedManager)
        editor.apply()
    }

    fun getSavedManager(): Boolean {
        return pref.getBoolean("isSavedManager", false)
    }
}