package ya.school.domain.repository

interface INetworkRepository {
    suspend fun login(
        email: String,
        password: String
    ): ya.school.common.logic.entity.DataResult<String>
}