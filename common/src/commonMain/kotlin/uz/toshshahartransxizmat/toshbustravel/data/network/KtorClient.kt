package uz.toshshahartransxizmat.toshbustravel.data.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.parameters
import uz.toshshahartransxizmat.toshbustravel.data.model.NewsDTO
import uz.toshshahartransxizmat.toshbustravel.util.API_KEY
import uz.toshshahartransxizmat.toshbustravel.util.BASE_URL

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

    override fun close() {
        client.close()
    }
}