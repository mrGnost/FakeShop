package ya.school.domain.usecase

import ya.school.common.logic.entity.DataResult
import ya.school.domain.entity.ProductFull
import ya.school.domain.repository.INetworkRepository
import ya.school.domain.usecase.api.IGetProductInfoUseCase
import javax.inject.Inject

class GetProductInfoUseCase @Inject constructor(
    private val networkRepository: INetworkRepository
) : IGetProductInfoUseCase {
    override suspend fun invoke(id: String): DataResult<ProductFull> {
        return networkRepository.getProductInfo(id)
    }
}