package uz.toshshahartransxizmat.toshbustravel.di

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import uz.toshshahartransxizmat.toshbustravel.components.otp.viewModel.OtpViewModel
import uz.toshshahartransxizmat.toshbustravel.data.network.KtorClient
import uz.toshshahartransxizmat.toshbustravel.data.network.KtorService
import uz.toshshahartransxizmat.toshbustravel.data.repository.NetworkRepositoryImpl
import uz.toshshahartransxizmat.toshbustravel.domain.repository.NetworkRepository
import uz.toshshahartransxizmat.toshbustravel.domain.usecase.AllUseCases
import uz.toshshahartransxizmat.toshbustravel.domain.usecase.GetDetailsUseCase
import uz.toshshahartransxizmat.toshbustravel.domain.usecase.GetTransportsUseCase
import uz.toshshahartransxizmat.toshbustravel.domain.usecase.PasswordVerifyUseCase
import uz.toshshahartransxizmat.toshbustravel.domain.usecase.PostSignInUseCase
import uz.toshshahartransxizmat.toshbustravel.domain.usecase.PostSignUpUseCase
import uz.toshshahartransxizmat.toshbustravel.domain.usecase.ResetPasswordUseCase
import uz.toshshahartransxizmat.toshbustravel.domain.usecase.amount.CalculatorUseCase
import uz.toshshahartransxizmat.toshbustravel.domain.usecase.order.ActiveOrderUseCase
import uz.toshshahartransxizmat.toshbustravel.domain.usecase.order.CreateOrderUseCase
import uz.toshshahartransxizmat.toshbustravel.domain.usecase.order.GetOrdersUseCase
import uz.toshshahartransxizmat.toshbustravel.domain.usecase.order.PayOrderUseCase
import uz.toshshahartransxizmat.toshbustravel.domain.usecase.profile.GetClientUseCase
import uz.toshshahartransxizmat.toshbustravel.domain.usecase.profile.UpdateClientUseCase
import uz.toshshahartransxizmat.toshbustravel.share.SettingsSource
import uz.toshshahartransxizmat.toshbustravel.share.getSettingsSource
import uz.toshshahartransxizmat.toshbustravel.ui.amount.viewModel.AmountViewModel
import uz.toshshahartransxizmat.toshbustravel.ui.auth.viewModel.AuthViewModel
import uz.toshshahartransxizmat.toshbustravel.ui.home.viewModel.HomeViewModel
import uz.toshshahartransxizmat.toshbustravel.ui.orders.viewModel.ActiveOrderViewModel
import uz.toshshahartransxizmat.toshbustravel.ui.orders.viewModel.OrderViewModel
import uz.toshshahartransxizmat.toshbustravel.ui.payment.viewModel.PaymentViewModel
import uz.toshshahartransxizmat.toshbustravel.ui.profile.viewModel.ProfileViewModel
import uz.toshshahartransxizmat.toshbustravel.ui.transportDetails.viewModel.DetailsViewModel

val appModule = module {
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    useAlternativeNames = false
                })
            }
        }
    }

    single<SettingsSource> {
        getSettingsSource()
    }

    factory<KtorService> {
        KtorClient(client = get(), settings = get())
    }

    singleOf(::NetworkRepositoryImpl) {
        bind<NetworkRepository>()
    }

    factory {
        AllUseCases(
            getTransportsUseCase = GetTransportsUseCase(),
            postSignUpUseCase = PostSignUpUseCase(),
            postSignInUseCase = PostSignInUseCase(),
            getDetailsUseCase = GetDetailsUseCase(),
            resetPasswordUseCase = ResetPasswordUseCase(),
            passwordVerifyUseCase = PasswordVerifyUseCase(),
            getClientUseCase = GetClientUseCase(),
            updateClientUseCase = UpdateClientUseCase(),
            createOrderUseCase = CreateOrderUseCase(),
            activeOrderUseCase = ActiveOrderUseCase(),
            payOrderUseCase = PayOrderUseCase(),
            getOrdersUseCase = GetOrdersUseCase(),
            calculatorUseCase = CalculatorUseCase()
        )
    }

    factory {
        HomeViewModel(get())
    }

    factory {
        AuthViewModel(get())
    }

    factory {
        OtpViewModel()
    }

    factory {
        DetailsViewModel(get())
    }

    factory {
        ProfileViewModel(get())
    }

    factory {
        OrderViewModel(get())
    }

    factory {
        AmountViewModel(get())
    }

    factory {
        ActiveOrderViewModel(get())
    }

    factory {
        PaymentViewModel(get())
    }
}
