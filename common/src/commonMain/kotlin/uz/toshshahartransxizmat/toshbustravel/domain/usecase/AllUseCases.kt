package uz.toshshahartransxizmat.toshbustravel.domain.usecase

import uz.toshshahartransxizmat.toshbustravel.domain.usecase.amount.CalculatorUseCase
import uz.toshshahartransxizmat.toshbustravel.domain.usecase.card.AddCardUseCase
import uz.toshshahartransxizmat.toshbustravel.domain.usecase.card.GetCardsUseCase
import uz.toshshahartransxizmat.toshbustravel.domain.usecase.order.ActiveOrderUseCase
import uz.toshshahartransxizmat.toshbustravel.domain.usecase.order.CreateOrderUseCase
import uz.toshshahartransxizmat.toshbustravel.domain.usecase.order.GetOrdersUseCase
import uz.toshshahartransxizmat.toshbustravel.domain.usecase.order.PayOrderUseCase
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
    val updateClientUseCase: UpdateClientUseCase,
    val createOrderUseCase: CreateOrderUseCase,
    val activeOrderUseCase: ActiveOrderUseCase,
    val payOrderUseCase: PayOrderUseCase,
    val getOrdersUseCase: GetOrdersUseCase,
    val calculatorUseCase: CalculatorUseCase,
    val getCardsUseCase: GetCardsUseCase,
    val addCardUseCase: AddCardUseCase
)