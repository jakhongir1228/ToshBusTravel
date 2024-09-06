package uz.toshshahartransxizmat.toshbustravel.data.network

import androidx.compose.ui.unit.Constraints
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.parameters
import uz.toshshahartransxizmat.toshbustravel.data.model.VehicleDTO
import uz.toshshahartransxizmat.toshbustravel.data.model.response.LogInDTO
import uz.toshshahartransxizmat.toshbustravel.data.model.response.TransportsDTO
import uz.toshshahartransxizmat.toshbustravel.domain.model.request.SignInEntity
import uz.toshshahartransxizmat.toshbustravel.domain.model.request.SignUpEntity
import uz.toshshahartransxizmat.toshbustravel.util.API_VEHICLE_ALL
import uz.toshshahartransxizmat.toshbustravel.util.BASE_URL
import uz.toshshahartransxizmat.toshbustravel.util.SIGN_IN_ENDPOINT
import uz.toshshahartransxizmat.toshbustravel.util.SIGN_UP_ENDPOINT
import io.ktor.client.request.headers
import uz.toshshahartransxizmat.toshbustravel.share.SettingsSource
import uz.toshshahartransxizmat.toshbustravel.util.ACCESS_TOKEN_KEY

class KtorClient(
    private val client: HttpClient,
    private val settings: SettingsSource
) : KtorService {

    override suspend fun getVehicles(query: String, page: Int, size: Int): VehicleDTO {
        val url = "$BASE_URL$API_VEHICLE_ALL"
        val token = settings.getValue(ACCESS_TOKEN_KEY)

        val response: HttpResponse = client.get(url) {
            headers {
                append("Authorization", "Bearer $token")
            }
            parameters {
                parameter("type", query)
                parameter("page", page)
                parameter("size", size)
            }
        }

        println("Response status---->: ${response.status}")
        val responseBodyText = response.bodyAsText()
        println("Response content--->: $responseBodyText")

        if (response.contentType() != ContentType.Application.Json) {
            throw IllegalArgumentException("Unexpected content type: ${response.contentType()}")
        }

        return response.body()
    }

    override suspend fun postSignUp(signUpEntity: SignUpEntity): TransportsDTO {
        val url = BASE_URL + SIGN_UP_ENDPOINT
        val r = client.post(url) {
            contentType(ContentType.Application.Json)
            setBody(signUpEntity)
        }
        return r.body()
    }

    override suspend fun postSignIn(signInEntity: SignInEntity): LogInDTO {
        val url = BASE_URL + SIGN_IN_ENDPOINT

        val r = client.post(url) {
            contentType(ContentType.Application.Json)
            setBody(signInEntity)
        }

        println("Response status---->: ${r.status}")
        println("Response content--->: ${r.bodyAsText()}")

        if (r.contentType() != ContentType.Application.Json) {
            throw IllegalArgumentException("Unexpected content type: ${r.contentType()}")
        }

        return r.body()
    }

    override fun close() {
        client.close()
    }
}