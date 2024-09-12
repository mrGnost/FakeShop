package ya.school.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ya.school.common.logic.entity.DataResult
import ya.school.data.mappers.DTOMappers
import ya.school.data.model.AuthDataDTO
import ya.school.data.model.AuthTokenDTO
import ya.school.data.model.ProductShortDTO
import ya.school.data.model.RegistrationDataDTO
import ya.school.data.network.ShopApi
import ya.school.data.util.NetworkUtil
import ya.school.domain.repository.INetworkRepository
import javax.inject.Inject

internal class NetworkRepository @Inject constructor(
    private val api: ShopApi,
    private val mapper: DTOMappers
) : INetworkRepository {
    override suspend fun login(
        email: String,
        password: String
    ): DataResult<String> = withContext(Dispatchers.IO) {
        NetworkUtil.getResponse(
            mapper = { token: AuthTokenDTO -> token.token }
        ) {
            api.login(
                AuthDataDTO(email, password)
            )
        }
    }

    override suspend fun register(
        name: String,
        email: String,
        password: String,
        repeatedPassword: String
    ): DataResult<Unit> = withContext(Dispatchers.IO) {
        NetworkUtil.getResponse(
            mapper = { }
        ) {
            api.register(
                RegistrationDataDTO(name, email, password, repeatedPassword)
            )
        }
    }

    override suspend fun getProducts(
        category: String?,
        limit: Int?,
        page: Int?
    ) = withContext(Dispatchers.IO) {
        NetworkUtil.getResponse(
            mapper = mapper::productShortDTOListToDomain
        ) {
            api.getProducts(category, limit, page)
        }
    }
}