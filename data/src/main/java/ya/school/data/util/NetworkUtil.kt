package ya.school.data.util

import retrofit2.Response
import ya.school.domain.entity.DataResult

object NetworkUtil {
    suspend fun<K, R, T : Response<K>> getResponse(
        mapper: (K) -> R,
        request: suspend () -> T
    ): DataResult<R> {
        try {
            val response = request()
            return when (response.code()) {
                400 -> DataResult.Error("Ошибка синхронизации данных")
                401 -> DataResult.Error("Ошибка авторизации")
                404 -> DataResult.Error("Ошибка: элемент не найден")
                500 -> DataResult.Error("Ошибка на стороне сервера")
                else -> {
                    response.body()?.let {
                        DataResult.Success(mapper(it))
                    } ?: DataResult.Error("Ошибка: пустой ответ от сервера")
                }
            }
        } catch (e: Exception) {
            return DataResult.Error("Не удалось связаться с сервером...")
        }
    }
}