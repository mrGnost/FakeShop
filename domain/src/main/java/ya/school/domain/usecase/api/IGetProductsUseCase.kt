package ya.school.domain.usecase.api

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ya.school.domain.entity.ProductShort

interface IGetProductsUseCase {
    suspend operator fun invoke(
        category: String? = null,
        sort: String? = null,
        limit: Int = 20
    ): Flow<PagingData<ProductShort>>
}