package ya.school.data.network

import androidx.paging.PagingSource
import androidx.paging.PagingSource.LoadResult.Page
import androidx.paging.PagingState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import ya.school.common.logic.entity.DataResult
import ya.school.data.mappers.DTOMappers
import ya.school.data.util.NetworkUtil
import ya.school.domain.entity.ProductShort
import java.io.IOException

internal class ShopPagingSource(
    private val shopApi: ShopApi,
    private val mapper: DTOMappers,
    private val category: String? = null,
    private val sort: String? = null
) : PagingSource<Int, ProductShort>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProductShort> =
        withContext(Dispatchers.IO) {
            try {
                val pageNumber = params.key ?: 1

                return@withContext NetworkUtil.getResponse(
                    mapper = mapper::productShortDTOListToDomain
                ) {
                    shopApi.getProducts(
                        category = category,
                        sort = sort,
                        limit = params.loadSize,
                        page = pageNumber
                    )
                }.let { result ->
                    when (result) {
                        is DataResult.Error -> throw IOException("Empty response")
                        is DataResult.Success -> Page(
                            data = result.data,
                            prevKey = if (pageNumber > 1) pageNumber else null,
                            nextKey = pageNumber + 1
                        )
                    }
                }
            } catch (e: IOException) {
                LoadResult.Error(e)
            } catch (e: HttpException) {
                LoadResult.Error(e)
            }
        }

    override fun getRefreshKey(state: PagingState<Int, ProductShort>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}