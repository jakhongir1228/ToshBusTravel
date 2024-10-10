package uz.toshshahartransxizmat.toshbustravel.data.network

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
import uz.toshshahartransxizmat.toshbustravel.domain.model.request.SignInEntity
import uz.toshshahartransxizmat.toshbustravel.domain.model.request.SignUpEntity
import uz.toshshahartransxizmat.toshbustravel.util.API_VEHICLE_ALL
import uz.toshshahartransxizmat.toshbustravel.util.BASE_URL
import uz.toshshahartransxizmat.toshbustravel.util.SIGN_IN_ENDPOINT
import uz.toshshahartransxizmat.toshbustravel.util.SIGN_UP_ENDPOINT
import io.ktor.client.request.headers
import uz.toshshahartransxizmat.toshbustravel.data.model.response.ActiveOrderDTO
import uz.toshshahartransxizmat.toshbustravel.data.model.response.AddCardDTO
import uz.toshshahartransxizmat.toshbustravel.data.model.response.CalculatorDTO
import uz.toshshahartransxizmat.toshbustravel.data.model.response.CardsDTO
import uz.toshshahartransxizmat.toshbustravel.data.model.response.ClientDTO
import uz.toshshahartransxizmat.toshbustravel.data.model.response.OrderDTO
import uz.toshshahartransxizmat.toshbustravel.data.model.response.PaymentDTO
import uz.toshshahartransxizmat.toshbustravel.data.model.response.ResetDTO
import uz.toshshahartransxizmat.toshbustravel.data.model.response.SignUpDTO
import uz.toshshahartransxizmat.toshbustravel.data.model.response.TransportDTO
import uz.toshshahartransxizmat.toshbustravel.data.model.response.VerifyCardDTO
import uz.toshshahartransxizmat.toshbustravel.domain.model.request.AddCardEntity
import uz.toshshahartransxizmat.toshbustravel.domain.model.request.CalculatorEntity
import uz.toshshahartransxizmat.toshbustravel.domain.model.request.CreateOrderEntity
import uz.toshshahartransxizmat.toshbustravel.domain.model.request.PayOrderEntity
import uz.toshshahartransxizmat.toshbustravel.domain.model.request.ResetEntity
import uz.toshshahartransxizmat.toshbustravel.domain.model.request.UserProfileEntity
import uz.toshshahartransxizmat.toshbustravel.domain.model.request.VerifyCardEntity
import uz.toshshahartransxizmat.toshbustravel.share.SettingsSource
import uz.toshshahartransxizmat.toshbustravel.util.ACCESS_TOKEN_KEY
import uz.toshshahartransxizmat.toshbustravel.util.API_CALCULATOR
import uz.toshshahartransxizmat.toshbustravel.util.API_CARD
import uz.toshshahartransxizmat.toshbustravel.util.API_CLIENT
import uz.toshshahartransxizmat.toshbustravel.util.API_ORDER
import uz.toshshahartransxizmat.toshbustravel.util.API_RESET_PASSWORD
import uz.toshshahartransxizmat.toshbustravel.util.API_RESET_PASSWORD_VERIFY
import uz.toshshahartransxizmat.toshbustravel.util.API_VEHICLE_DETAILS

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

        if (response.contentType() != ContentType.Application.Json) {
            throw IllegalArgumentException("Unexpected content type: ${response.contentType()}")
        }

        return response.body()
    }

    override suspend fun postSignUp(signUpEntity: SignUpEntity): SignUpDTO {
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

        if (r.contentType() != ContentType.Application.Json) {
            throw IllegalArgumentException("Unexpected content type: ${r.contentType()}")
        }

        return r.body()
    }

    override suspend fun postResetPassword(resetEntity: ResetEntity): ResetDTO {
        val url = "$BASE_URL$API_RESET_PASSWORD"

        val r = client.post(url) {
            contentType(ContentType.Application.Json)
            setBody(resetEntity)
        }

        if (r.contentType() != ContentType.Application.Json) {
            throw IllegalArgumentException("Unexpected content type: ${r.contentType()}")
        }

        return r.body()
    }

    override suspend fun postPasswordVerify(resetEntity: ResetEntity): ResetDTO {
        val url = "$BASE_URL$API_RESET_PASSWORD_VERIFY"

        val r = client.post(url) {
            contentType(ContentType.Application.Json)
            setBody(resetEntity)
        }

        if (r.contentType() != ContentType.Application.Json) {
            throw IllegalArgumentException("Unexpected content type: ${r.contentType()}")
        }

        return r.body()
    }

    override suspend fun getDetails(id: Int): TransportDTO {
        val url = "$BASE_URL$API_VEHICLE_DETAILS"
        val token = settings.getValue(ACCESS_TOKEN_KEY)

        val response: HttpResponse = client.get(url) {
            headers {
                append("Authorization", "Bearer $token")
            }
            parameters {
                parameter("id", id)
            }
        }

        if (response.contentType() != ContentType.Application.Json) {
            throw IllegalArgumentException("Unexpected content type: ${response.contentType()}")
        }

        return response.body()
    }

    override suspend fun getClientInfo(): ClientDTO {
        val url = "$BASE_URL$API_CLIENT/info"
        val token = settings.getValue(ACCESS_TOKEN_KEY)

        val response: HttpResponse = client.get(url) {
            headers {
                append("Authorization", "Bearer $token")
            }
        }

        if (response.contentType() != ContentType.Application.Json) {
            throw IllegalArgumentException("Unexpected content type: ${response.contentType()}")
        }

        return response.body()
    }

    override suspend fun postUpdateClient(userProfileEntity: UserProfileEntity): ClientDTO {
        val url = "$BASE_URL$API_CLIENT/update"
        val token = settings.getValue(ACCESS_TOKEN_KEY)

        val r = client.post(url) {
            headers {
                append("Authorization", "Bearer $token")
            }
            contentType(ContentType.Application.Json)
            setBody(userProfileEntity)
        }

        if (r.contentType() != ContentType.Application.Json) {
            throw IllegalArgumentException("Unexpected content type: ${r.contentType()}")
        }

        return r.body()
    }

    override suspend fun postCreateOrder(createOrderEntity: CreateOrderEntity): OrderDTO {
        val url = "$BASE_URL$API_ORDER"
        val token = settings.getValue(ACCESS_TOKEN_KEY)

        val r = client.post(url) {
            headers {
                append("Authorization", "Bearer $token")
            }
            contentType(ContentType.Application.Json)
            setBody(createOrderEntity)
        }

        if (r.contentType() != ContentType.Application.Json) {
            throw IllegalArgumentException("Unexpected content type: ${r.contentType()}")
        }

        return r.body()
    }

    override suspend fun getActiveOrder(): ActiveOrderDTO {
        val url = "$BASE_URL$API_ORDER/active"

        val token = settings.getValue(ACCESS_TOKEN_KEY)

        val response: HttpResponse = client.get(url) {
            headers {
                append("Authorization", "Bearer $token")
            }
        }

        if (response.contentType() != ContentType.Application.Json) {
            throw IllegalArgumentException("Unexpected content type: ${response.contentType()}")
        }

        return response.body()
    }

    override suspend fun postPayOrder(payOrderEntity: PayOrderEntity): PaymentDTO {
        val url = "$BASE_URL$API_ORDER/pay"
        val token = settings.getValue(ACCESS_TOKEN_KEY)

        val r = client.post(url) {
            headers {
                append("Authorization", "Bearer $token")
            }
            contentType(ContentType.Application.Json)
            setBody(payOrderEntity)
        }

        if (r.contentType() != ContentType.Application.Json) {
            throw IllegalArgumentException("Unexpected content type: ${r.contentType()}")
        }

        return r.body()
    }

    override suspend fun getOrders(): OrderDTO {
        val url = "$BASE_URL$API_ORDER"

        val token = settings.getValue(ACCESS_TOKEN_KEY)

        val response: HttpResponse = client.get(url) {
            headers {
                append("Authorization", "Bearer $token")
            }
        }

        if (response.contentType() != ContentType.Application.Json) {
            throw IllegalArgumentException("Unexpected content type: ${response.contentType()}")
        }

        return response.body()
    }

    override suspend fun postCalculator(calculatorEntity: CalculatorEntity): CalculatorDTO {
        val url = "$BASE_URL$API_CALCULATOR"
        val token = settings.getValue(ACCESS_TOKEN_KEY)

        val r = client.post(url) {
            headers {
                append("Authorization", "Bearer $token")
            }
            contentType(ContentType.Application.Json)
            setBody(calculatorEntity)
        }

        if (r.contentType() != ContentType.Application.Json) {
            throw IllegalArgumentException("Unexpected content type: ${r.contentType()}")
        }

        return r.body()
    }

    override suspend fun getCards(): CardsDTO {
        val url = "$BASE_URL$API_CARD"

        val token = settings.getValue(ACCESS_TOKEN_KEY)

        val response: HttpResponse = client.get(url) {
            headers {
                append("Authorization", "Bearer $token")
            }
        }

        if (response.contentType() != ContentType.Application.Json) {
            throw IllegalArgumentException("Unexpected content type: ${response.contentType()}")
        }

        return response.body()
    }

    override suspend fun postAddCard(addCardEntity: AddCardEntity): AddCardDTO {
        val url = "$BASE_URL$API_CARD/add"
        val token = settings.getValue(ACCESS_TOKEN_KEY)

        val r = client.post(url) {
            headers {
                append("Authorization", "Bearer $token")
            }
            contentType(ContentType.Application.Json)
            setBody(addCardEntity)
        }

        if (r.contentType() != ContentType.Application.Json) {
            throw IllegalArgumentException("Unexpected content type: ${r.contentType()}")
        }

        return r.body()
    }

    override suspend fun postVerifyCard(verifyCardEntity: VerifyCardEntity): VerifyCardDTO {
        val url = "$BASE_URL$API_CARD/verify"
        val token = settings.getValue(ACCESS_TOKEN_KEY)

        val r = client.post(url) {
            headers {
                append("Authorization", "Bearer $token")
            }
            contentType(ContentType.Application.Json)
            setBody(verifyCardEntity)
        }

        if (r.contentType() != ContentType.Application.Json) {
            throw IllegalArgumentException("Unexpected content type: ${r.contentType()}")
        }

        return r.body()
    }

    override fun close() {
        client.close()
    }
}