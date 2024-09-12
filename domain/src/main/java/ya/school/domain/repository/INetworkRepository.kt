package ya.school.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ya.school.common.logic.entity.DataResult
import ya.school.domain.entity.ProductFull
import ya.school.domain.entity.ProductShort

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

    suspend fun getProducts(
        limit: Int,
        category: String? = null,
        sort: String? = null
    ): Flow<PagingData<ProductShort>>

    suspend fun getProductInfo(
        id: String
    ): DataResult<ProductFull>
}