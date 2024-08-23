package uz.toshshahartransxizmat.toshbustravel.di

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import uz.toshshahartransxizmat.toshbustravel.data.network.KtorClient
import uz.toshshahartransxizmat.toshbustravel.data.network.KtorService
import uz.toshshahartransxizmat.toshbustravel.data.repository.NetworkRepositoryImpl
import uz.toshshahartransxizmat.toshbustravel.domain.repository.NetworkRepository
import uz.toshshahartransxizmat.toshbustravel.domain.usecase.AllUseCases
import uz.toshshahartransxizmat.toshbustravel.domain.usecase.GetNewsUseCase
import uz.toshshahartransxizmat.toshbustravel.ui.home.viewModel.HomeViewModel

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
    factory<KtorService> {
        KtorClient(get())
    }
    singleOf(::NetworkRepositoryImpl) {
        bind<NetworkRepository>()
    }

    factory {
        AllUseCases(
            getNewsUseCase = GetNewsUseCase()
        )
    }

    factory {
        HomeViewModel(get())
    }
}