package ya.school.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ya.school.data.model.AuthDataDTO
import ya.school.data.model.AuthTokenDTO
import ya.school.data.network.ShopApi
import ya.school.data.util.NetworkUtil
import ya.school.common.logic.entity.DataResult
import ya.school.domain.repository.INetworkRepository
import javax.inject.Inject

internal class NetworkRepository @Inject constructor(
    private val api: ShopApi
) : INetworkRepository {
    override suspend fun login(email: String, password: String): DataResult<String> =
        withContext(Dispatchers.IO) {
            NetworkUtil.getResponse(
                mapper = { token: AuthTokenDTO -> token.token }
            ) {
                api.login(
                    AuthDataDTO(email, password)
                )
            }
        }
}