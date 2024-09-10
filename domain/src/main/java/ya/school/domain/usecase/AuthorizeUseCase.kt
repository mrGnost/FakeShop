package ya.school.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import ya.school.common.logic.entity.DataResult
import ya.school.domain.repository.IDatastoreRepository
import ya.school.domain.repository.INetworkRepository
import ya.school.domain.usecase.api.IAuthorizeUseCase
import javax.inject.Inject

internal class AuthorizeUseCase @Inject constructor(
    private val networkRepository: INetworkRepository,
    private val datastoreRepository: IDatastoreRepository
) : IAuthorizeUseCase {
    override suspend operator fun invoke(email: String, password: String): Flow<DataResult<Unit>> {
        networkRepository.login(email, password).let { result ->
            when (result) {
                is DataResult.Error -> return flowOf(result)
                is DataResult.Success -> {
                    datastoreRepository.saveToken(result.data)
                }
            }
        }
        return flowOf(DataResult.Success(Unit))
    }
}