package ya.school.domain.repository

interface IDatastoreRepository {
    suspend fun saveToken(token: String)
    suspend fun getToken(): String
}