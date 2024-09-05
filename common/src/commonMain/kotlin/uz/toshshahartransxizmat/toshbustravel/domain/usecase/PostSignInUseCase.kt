package uz.toshshahartransxizmat.toshbustravel.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import uz.toshshahartransxizmat.toshbustravel.domain.model.request.SignInEntity
import uz.toshshahartransxizmat.toshbustravel.domain.model.response.SignInData
import uz.toshshahartransxizmat.toshbustravel.domain.repository.NetworkRepository

typealias PostSingInBaseUseCase = BaseUseCase<SignInEntity, Flow<SignInData>>

class PostSignInUseCase: KoinComponent, PostSingInBaseUseCase {

    private val repository: NetworkRepository by inject()

    override suspend fun invoke(parameter: SignInEntity): Flow<SignInData> {
        return repository.postSignIn(parameter)
    }
}