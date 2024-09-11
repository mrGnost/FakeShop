package ya.school.data.model

import com.google.gson.annotations.SerializedName

data class RegistrationDataDTO(
    val name: String,
    val email: String,
    val password: String,
    @SerializedName("cpassword") val repeatedPassword: String
)
