package ya.school.domain.usecase

import ya.school.common.logic.entity.DataResult
import ya.school.domain.repository.IDatastoreRepository
import ya.school.domain.repository.INetworkRepository
import ya.school.domain.usecase.api.IAuthorizeUseCase
import javax.inject.Inject

internal class AuthorizeUseCase @Inject constructor(
    private val networkRepository: INetworkRepository,
    private val datastoreRepository: IDatastoreRepository
) : IAuthorizeUseCase {
    override suspend operator fun invoke(email: String, password: String): DataResult<Unit> {
        networkRepository.login(email, password).let { result ->
            when (result) {
                is DataResult.Error -> return result
                is DataResult.Success -> {
                    datastoreRepository.saveToken(result.data)
                }
            }
        }
        return DataResult.Success(Unit)
    }
}