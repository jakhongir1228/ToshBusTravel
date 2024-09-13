package uz.toshshahartransxizmat.toshbustravel.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import uz.toshshahartransxizmat.toshbustravel.domain.model.request.SignUpEntity
import uz.toshshahartransxizmat.toshbustravel.domain.model.response.AuthResponseData
import uz.toshshahartransxizmat.toshbustravel.domain.repository.NetworkRepository

typealias PostSignUpBaseUseCase = BaseUseCase<SignUpEntity, Flow<AuthResponseData>>

class PostSignUpUseCase: KoinComponent,  PostSignUpBaseUseCase{

    private val repository: NetworkRepository by inject()

    override suspend fun invoke(parameter: SignUpEntity): Flow<AuthResponseData> {
        return repository.postSignUp(parameter)
    }
}