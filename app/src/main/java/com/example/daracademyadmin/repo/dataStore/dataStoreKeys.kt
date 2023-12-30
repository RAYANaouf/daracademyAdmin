package com.example.daracademyadmin.repo.dataStore

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object dataStoreKeys {

    val Key_chatFeatureId = stringPreferencesKey("chatFeatureId")
    val Key_signIn        = booleanPreferencesKey("signIn")

}