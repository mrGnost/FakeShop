package ya.school.domain.usecase.api

import ya.school.common.logic.entity.DataResult
import ya.school.domain.entity.ProductFull

interface IGetProductInfoUseCase {
    suspend operator fun invoke(id: String): DataResult<ProductFull>
}