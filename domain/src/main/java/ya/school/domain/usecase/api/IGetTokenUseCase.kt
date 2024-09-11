package ya.school.domain.usecase.api

interface IGetTokenUseCase {
    suspend operator fun invoke(): String?
}