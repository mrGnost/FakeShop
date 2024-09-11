package ya.school.domain.repository

import ya.school.common.logic.entity.DataResult

interface INetworkRepository {
    suspend fun login(
        email: String,
        password: String
    ): DataResult<String>

    suspend fun register(
        name: String,
        email: String,
        password: String,
        repeatedPassword: String
    ): DataResult<Unit>
}