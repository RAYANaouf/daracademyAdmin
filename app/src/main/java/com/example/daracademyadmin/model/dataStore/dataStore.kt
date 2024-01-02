package com.example.daracademyadmin.model.dataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStoreFile
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.preferencesDataStoreFile


object dataStore {
    private var instance: DataStore<Preferences>? = null

    fun getInstance(context: Context): DataStore<Preferences> {
        return instance ?: createDataStore(context).also { instance = it }
    }

    private fun createDataStore(context: Context): DataStore<Preferences> {
        return context.preferencesDataStore
    }
}