package ya.school.data.network

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import ya.school.data.model.AuthDataDTO
import ya.school.data.model.AuthTokenDTO
import ya.school.data.model.ProductFullContainerDTO
import ya.school.data.model.ProductShortListDTO
import ya.school.data.model.RegistrationDataDTO

internal interface ShopApi {
    @POST("users/auth/login")
    suspend fun login(
        @Body authData: AuthDataDTO
    ): Response<AuthTokenDTO>

    @POST("users")
    suspend fun register(
        @Body registrationData: RegistrationDataDTO
    ): Response<Unit>

    @GET("products")
    suspend fun getProducts(
        @Query("category") category: String? = null,
        @Query("limit") limit: Int? = null,
        @Query("page") page: Int? = null,
        @Query("sort") sort: String? = null
    ): Response<ProductShortListDTO>

    @GET("products/{id}")
    suspend fun getProductInfo(
        @Path("id") id: String
    ): Response<ProductFullContainerDTO>
}