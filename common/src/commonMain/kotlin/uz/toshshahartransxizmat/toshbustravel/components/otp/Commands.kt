package uz.toshshahartransxizmat.toshbustravel.components.otp

sealed interface Commands {
    // Timer
    object TimerCompleted : Commands

    // Otp
    object RequestOtp : Commands
    class InputOtpCompleted(val value: String) : Commands

    // Navigation
    object ExitScreen : Commands
}
