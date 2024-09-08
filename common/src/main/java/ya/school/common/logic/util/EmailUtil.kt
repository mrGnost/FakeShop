package ya.school.common.logic.util

object EmailUtil {
    private const val EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9+_.-]+\$"

    fun isValid(email: String): Boolean {
        return email.matches(EMAIL_REGEX.toRegex())
    }
}