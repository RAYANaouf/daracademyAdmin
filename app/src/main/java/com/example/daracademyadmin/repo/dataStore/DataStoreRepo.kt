package com.example.daracademyadmin.repo.dataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

class DataStoreRepo {

    private val context : Context

    // At the top level of your kotlin file:
    private val Context.dataStore  : DataStore<Preferences> by preferencesDataStore(name = "dataStore")


    constructor(context: Context){
        this.context  = context
    }

    suspend fun getChatId() : String?{
        return context.dataStore.data.first()[dataStoreKeys.Key_chatFeatureId] ?: null
    }

    suspend fun createChatFeatureId(id : String) {

        context.dataStore.edit {
            it[dataStoreKeys.Key_chatFeatureId] = id
        }
    }

}