package ya.school.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ya.school.data.repository.DatastoreRepository
import ya.school.data.repository.NetworkRepository
import ya.school.domain.repository.IDatastoreRepository
import ya.school.domain.repository.INetworkRepository

@Module
@InstallIn(SingletonComponent::class)
internal interface BindModule {
    @Binds
    fun bindNetworkRepository(repository: NetworkRepository): INetworkRepository
    @Binds
    fun bindDatastoreRepository(repository: DatastoreRepository): IDatastoreRepository
}