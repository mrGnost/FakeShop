package ya.school.domain.usecase.api

import ya.school.common.logic.entity.DataResult

interface IRegisterUseCase {
    suspend operator fun invoke(
        name: String,
        email: String,
        password: String,
        repeatedPassword: String
    ): DataResult<Unit>
}