package uz.toshshahartransxizmat.toshbustravel.domain.usecase

import uz.toshshahartransxizmat.toshbustravel.domain.usecase.profile.GetClientUseCase
import uz.toshshahartransxizmat.toshbustravel.domain.usecase.profile.UpdateClientUseCase

data class AllUseCases(
    val getTransportsUseCase: GetTransportsUseCase,
    val postSignUpUseCase: PostSignUpUseCase,
    val postSignInUseCase: PostSignInUseCase,
    val getDetailsUseCase: GetDetailsUseCase,
    val resetPasswordUseCase: ResetPasswordUseCase,
    val passwordVerifyUseCase: PasswordVerifyUseCase,
    val getClientUseCase: GetClientUseCase,
    val updateClientUseCase: UpdateClientUseCase
)