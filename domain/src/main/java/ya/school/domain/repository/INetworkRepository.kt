package ya.school.domain.repository

import ya.school.domain.entity.DataResult

interface INetworkRepository {
    suspend fun login(email: String, password: String): DataResult<String>
}