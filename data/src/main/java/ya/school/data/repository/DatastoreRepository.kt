package ya.school.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import ya.school.domain.repository.IDatastoreRepository
import javax.inject.Inject

internal class DatastoreRepository @Inject constructor(
    private val datastore: DataStore<Preferences>
) : IDatastoreRepository {
    override suspend fun saveToken(token: String) {
        withContext(Dispatchers.IO) {
            datastore.edit { settings ->
                settings[tokenKey] = token
            }
        }
    }

    override suspend fun getToken(): String? {
        return datastore.data.map { preferences ->
            preferences[tokenKey] ?: ""
        }.firstOrNull()
    }

    companion object {
        val tokenKey = stringPreferencesKey("token_key")
    }
}