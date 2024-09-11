package ya.school.domain.usecase

import ya.school.domain.repository.IDatastoreRepository
import ya.school.domain.usecase.api.IGetTokenUseCase
import javax.inject.Inject

internal class GetTokenUseCase @Inject constructor(
    private val datastoreRepository: IDatastoreRepository
) : IGetTokenUseCase {
    override suspend fun invoke(): String? {
        return datastoreRepository.getToken()
    }

}