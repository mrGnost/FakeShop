package ya.school.common.logic.entity

import androidx.annotation.StringRes
import ya.school.common.R

enum class SortingMethod(
    @StringRes val stringId: Int,
    val request: String?
) {
    None(
        stringId = R.string.no_sorting,
        request = null
    ),
    Ascending(
        stringId = R.string.ascending_sorting,
        request = "+price"
    ),
    Descending(
        stringId = R.string.descending_sorting,
        request = "-price"
    )
}