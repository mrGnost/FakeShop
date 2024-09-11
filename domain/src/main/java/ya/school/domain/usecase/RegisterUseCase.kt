package ya.school.domain.usecase

import ya.school.common.logic.entity.DataResult
import ya.school.domain.repository.INetworkRepository
import ya.school.domain.usecase.api.IRegisterUseCase
import javax.inject.Inject

internal class RegisterUseCase @Inject constructor(
    private val networkRepository: INetworkRepository
) : IRegisterUseCase {
    override suspend operator fun invoke(
        name: String,
        email: String,
        password: String,
        repeatedPassword: String
    ): DataResult<Unit> {
        return networkRepository.register(name, email, password, repeatedPassword)
    }
}