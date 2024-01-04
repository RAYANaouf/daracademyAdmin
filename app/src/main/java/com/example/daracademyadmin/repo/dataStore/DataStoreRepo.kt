package com.example.daracademyadmin.repo.dataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.daracademyadmin.model.dataStore.dataStore
import kotlinx.coroutines.flow.first

class DataStoreRepo {

    private val context : Context

    private val dataStoreInst : DataStore<Preferences>

    constructor(context: Context){
        this.context  = context
        dataStoreInst = dataStore.getInstance(context)
    }

    suspend fun isSignIn() : Boolean{
        return dataStoreInst.data.first()[dataStoreKeys.Key_signIn] ?: false
    }

    suspend fun saveSignIn() {

        dataStoreInst.edit {
            it[dataStoreKeys.Key_signIn] = true
        }
    }

    suspend fun getChatId() : String?{
        return dataStoreInst.data.first()[dataStoreKeys.Key_chatFeatureId] ?: null
    }

    suspend fun createChatFeatureId(id : String) {

        dataStoreInst.edit {
            it[dataStoreKeys.Key_chatFeatureId] = id
        }
    }



}