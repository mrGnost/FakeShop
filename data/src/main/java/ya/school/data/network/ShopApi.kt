package ya.school.data.network

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import ya.school.data.model.AuthDataDTO
import ya.school.data.model.AuthTokenDTO
import ya.school.data.model.RegistrationDataDTO

interface ShopApi {
    @POST("users/auth/login")
    suspend fun login(
        @Body authData: AuthDataDTO
    ): Response<AuthTokenDTO>

    @POST("users")
    suspend fun register(
        @Body registrationData: RegistrationDataDTO
    ): Response<Unit>
}