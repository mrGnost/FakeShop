package ya.school.domain.usecase

import ya.school.common.logic.entity.DataResult
import ya.school.domain.entity.ProductShort
import ya.school.domain.repository.INetworkRepository
import ya.school.domain.usecase.api.IGetProductsUseCase
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val networkRepository: INetworkRepository
) : IGetProductsUseCase {
    override suspend fun invoke(category: String?): DataResult<List<ProductShort>> {
        return networkRepository.getProducts(category = category)
    }
}