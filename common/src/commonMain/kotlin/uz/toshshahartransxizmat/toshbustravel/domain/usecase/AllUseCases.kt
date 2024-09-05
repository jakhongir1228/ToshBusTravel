package uz.toshshahartransxizmat.toshbustravel.domain.usecase

data class AllUseCases(
    val getNewsUseCase: GetNewsUseCase,
    val getTransportsUseCase: GetTransportsUseCase,
    val postSignUpUseCase: PostSignUpUseCase,
    val postSignInUseCase: PostSignInUseCase
)