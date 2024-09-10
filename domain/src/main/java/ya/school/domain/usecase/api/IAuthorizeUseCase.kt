package ya.school.domain.usecase.api

import kotlinx.coroutines.flow.Flow
import ya.school.common.logic.entity.DataResult

interface IAuthorizeUseCase {
    suspend operator fun invoke(email: String, password: String): Flow<DataResult<Unit>>
}