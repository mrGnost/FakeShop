package ya.school.domain.usecase.api

import ya.school.common.logic.entity.DataResult

interface IAuthorizeUseCase {
    suspend operator fun invoke(email: String, password: String): DataResult<Unit>
}