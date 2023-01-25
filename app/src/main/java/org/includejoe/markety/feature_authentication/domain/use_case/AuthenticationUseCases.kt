package org.includejoe.markety.feature_authentication.domain.use_case

data class AuthenticationUseCases(
    val login: LoginUseCase,
    val register: RegisterUseCase,
    val getNewAccessTokenUseCase: GetNewAccessTokenUseCase
)
