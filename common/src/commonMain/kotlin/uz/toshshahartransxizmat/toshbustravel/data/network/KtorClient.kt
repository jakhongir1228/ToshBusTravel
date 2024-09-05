package uz.toshshahartransxizmat.toshbustravel.data.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.parameters
import uz.toshshahartransxizmat.toshbustravel.data.model.NewsDTO
import uz.toshshahartransxizmat.toshbustravel.data.model.response.LogInDTO
import uz.toshshahartransxizmat.toshbustravel.data.model.response.TransportsDTO
import uz.toshshahartransxizmat.toshbustravel.domain.model.request.SignInEntity
import uz.toshshahartransxizmat.toshbustravel.domain.model.request.SignUpEntity
import uz.toshshahartransxizmat.toshbustravel.util.API_KEY
import uz.toshshahartransxizmat.toshbustravel.util.BASE_URL
import uz.toshshahartransxizmat.toshbustravel.util.SIGN_IN_ENDPOINT
import uz.toshshahartransxizmat.toshbustravel.util.SIGN_UP_ENDPOINT

class KtorClient(
    private val client: HttpClient
) : KtorService {
    override suspend fun getNews(query: String): NewsDTO {
        val r = client.get(BASE_URL) {
            parameters {
                parameter("q", query)
                parameter("apiKey", API_KEY)
            }
        }
        return r.body<NewsDTO>()
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

        // Add Authentication/Authorization header here if needed
        val r = client.post(url) {
            contentType(ContentType.Application.Json)
            setBody(signInEntity)
        }

        // Log response status and content for debugging
        println("Response status---->: ${r.status}")
        println("Response content--->: ${r.bodyAsText()}") // Log the raw JSON response

        // Ensure the response ContentType is JSON
        if (r.contentType() != ContentType.Application.Json) {
            throw IllegalArgumentException("Unexpected content type: ${r.contentType()}")
        }

        return r.body()
    }

    override fun close() {
        client.close()
    }
}