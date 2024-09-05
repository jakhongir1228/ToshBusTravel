package uz.toshshahartransxizmat.toshbustravel.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import uz.toshshahartransxizmat.toshbustravel.domain.model.request.SignUpEntity
import uz.toshshahartransxizmat.toshbustravel.domain.model.response.SignUpData
import uz.toshshahartransxizmat.toshbustravel.domain.repository.NetworkRepository

typealias PostSignUpBaseUseCase = BaseUseCase<SignUpEntity, Flow<SignUpData>>

class PostSignUpUseCase: KoinComponent,  PostSignUpBaseUseCase{

    private val repository: NetworkRepository by inject()

    override suspend fun invoke(parameter: SignUpEntity): Flow<SignUpData> {
        return repository.postSignUp(parameter)
    }
}