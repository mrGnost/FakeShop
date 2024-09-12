package ya.school.domain.repository

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
        category: String? = null,
        limit: Int? = null,
        page: Int? = null
    ): DataResult<List<ProductShort>>

    suspend fun getProductInfo(
        id: String
    ): DataResult<ProductFull>
}