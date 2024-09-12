package ya.school.domain.usecase

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ya.school.domain.entity.ProductShort
import ya.school.domain.repository.INetworkRepository
import ya.school.domain.usecase.api.IGetProductsUseCase
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val networkRepository: INetworkRepository
) : IGetProductsUseCase {
    override suspend fun invoke(
        category: String?,
        sort: String?,
        limit: Int
    ): Flow<PagingData<ProductShort>> {
        return networkRepository.getProducts(
            category = category,
            sort = sort,
            limit = limit
        )
    }
}