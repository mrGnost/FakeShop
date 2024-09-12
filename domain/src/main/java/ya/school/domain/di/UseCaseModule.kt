package ya.school.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ya.school.domain.usecase.AuthorizeUseCase
import ya.school.domain.usecase.GetProductsUseCase
import ya.school.domain.usecase.GetTokenUseCase
import ya.school.domain.usecase.RegisterUseCase
import ya.school.domain.usecase.api.IAuthorizeUseCase
import ya.school.domain.usecase.api.IGetProductsUseCase
import ya.school.domain.usecase.api.IGetTokenUseCase
import ya.school.domain.usecase.api.IRegisterUseCase

@Module
@InstallIn(SingletonComponent::class)
internal interface UseCaseModule {
    @Binds
    fun bindAuthorizeUseCase(authorizeUseCase: AuthorizeUseCase): IAuthorizeUseCase

    @Binds
    fun bindRegisterUseCase(registerUseCase: RegisterUseCase): IRegisterUseCase

    @Binds
    fun bindGetTokenUseCase(getTokenUseCase: GetTokenUseCase): IGetTokenUseCase

    @Binds
    fun bindGetProductsUseCase(getProductsUseCase: GetProductsUseCase): IGetProductsUseCase
}