package ya.school.domain.usecase.api

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ya.school.common.logic.entity.DataResult
import ya.school.domain.entity.ProductShort

interface IGetProductsUseCase {
    suspend operator fun invoke(
        category: String? = null,
        limit: Int = 20
    ): Flow<PagingData<ProductShort>>
}