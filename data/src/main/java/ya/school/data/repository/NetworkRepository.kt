package ya.school.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import ya.school.common.logic.entity.DataResult
import ya.school.data.mappers.DTOMappers
import ya.school.data.model.AuthDataDTO
import ya.school.data.model.AuthTokenDTO
import ya.school.data.model.RegistrationDataDTO
import ya.school.data.network.ShopApi
import ya.school.data.network.ShopPagingSource
import ya.school.data.util.NetworkUtil
import ya.school.domain.entity.ProductShort
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
        limit: Int,
        category: String?,
        sort: String?
    ): Flow<PagingData<ProductShort>> = Pager(
        PagingConfig(
            pageSize = limit,
            enablePlaceholders = false
        )
    ) {
        ShopPagingSource(
            shopApi = api,
            mapper = mapper,
            category = category,
            sort = sort
        )
    }.flow

    override suspend fun getProductInfo(id: String) = with(Dispatchers.IO) {
        NetworkUtil.getResponse(
            mapper = mapper::productFullDTOToDomain
        ) {
            api.getProductInfo(id)
        }
    }
}