package com.bakery.auth.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bakery.auth.data.repository.AuthRepository
import com.bakery.auth.domain.model.SignIn
import com.bakery.auth.domain.model.SignUp
import com.bakery.auth.domain.rules.AuthValidator
import com.bakery.auth.presentation.events.AuthEvents
import com.bakery.auth.presentation.state.AuthState
import com.bakery.auth.presentation.state.SignInState
import com.bakery.auth.presentation.state.SignUpState
import com.bakery.core.presentation.messages.Messages
import com.bakery.core.presentation.navigation.Destination
import com.bakery.core.presentation.navigation.Navigator
import com.bakery.core.shared.types.auth.SignInDto
import com.bakery.core.shared.types.auth.SignUpDto
import com.bakery.core.types.state.RequestState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AuthViewModel(
    private val navigator: Navigator,
    private val messages: Messages,
    private val authRepository: AuthRepository
) : ViewModel() {
    private val _signUpstate = MutableStateFlow(SignUpState())

    private val _signInstate = MutableStateFlow(SignInState())

    private var _state = MutableStateFlow(AuthState())
    val state = combine(
        _state,
        _signUpstate,
        _signInstate
    ) { state, signUpState, signInState ->
        state.copy(
            signUpState = signUpState,
            signInState = signInState
        )
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        AuthState()
    )

    fun onEvent(event: AuthEvents) {
        when (event) {
            is AuthEvents.OnSignInUsernameChanged -> signInUsernameChanged(event.value)
            is AuthEvents.OnSignInPasswordChanged -> signInPasswordChanged(event.value)
            AuthEvents.TogglePasswordVisibility -> togglePasswordVisibility()
            AuthEvents.OnSignInSubmit -> onSignInSubmit()
            is AuthEvents.OnSignUpFirstNameChanged -> signUpFirstNameChanged(event.value)
            is AuthEvents.OnSignUpLastNameChanged -> signUpLastNameChanged(event.value)
            is AuthEvents.OnSignUpUsernameChanged -> signUpUsernameChanged(event.value)
            is AuthEvents.OnSignUpEmailChanged -> signUpEmailChanged(event.value)
            is AuthEvents.OnSignUpPasswordChanged -> signUpPasswordChanged(event.value)
            is AuthEvents.OnSignUpPhoneNumberChanged -> signUpPhoneNumberChanged(event.value)
            is AuthEvents.OnSignUpBirthDateChanged -> signUpBirthDateChanged(event.value)
            is AuthEvents.OnSignUpAddress1Changed -> signUpAddress1Changed(event.value)
            is AuthEvents.OnSignUpAddress2Changed -> signUpAddress2Changed(event.value)
            is AuthEvents.OnSignUpGenderChanged -> signUpGenderChanged(event.value)
            AuthEvents.OnSignUpSubmit -> onSignUpSubmit()
        }
    }

    private fun signInUsernameChanged(value: String) {
        _signInstate.update { state ->
            state.copy(
                username = value,
                signInEnabled = state.username.isNotEmpty() && state.password.isNotEmpty()
            )
        }
    }

    private fun signInPasswordChanged(value: String) {
        _signInstate.update { state ->
            state.copy(
                password = value,
                signInEnabled = state.username.isNotEmpty() && state.password.isNotEmpty()
            )
        }
    }

    private fun togglePasswordVisibility() {
        _signInstate.update { state ->
            state.copy(
                passwordVisibility = !state.passwordVisibility
            )
        }
    }

    private fun onSignInSubmit() {
        if (onSignInError()) return
        viewModelScope.launch {
            val call = authRepository.signIn(
                signInDto = SignInDto(
                    username = _state.value.signInState.username,
                    password = _state.value.signInState.password
                )
            )
            when (call) {
                is RequestState.Error -> {
                    _state.update { state ->
                        state.copy(
                            isLoading = false,
                            errorMessage = call.error.message
                        )
                    }
                    messages.sendMessage(call.error)
                }
                is RequestState.Success -> {
                    _state.update { state ->
                        state.copy(
                            isLoading = false,
                            errorMessage = null
                        )
                    }

                    messages.sendMessage(call.data)
                    navigator.navigate(
                        destination = Destination.HomeGraph,
                        navOptions = {
                            popUpTo(Destination.AuthGraph) {
                                inclusive = true
                            }
                            launchSingleTop = true
                        }
                    )

                    resetState()
                }
                else -> {
                    _state.update { state ->
                        state.copy(
                            isLoading = true,
                            errorMessage = null
                        )
                    }
                }
            }
        }
    }

    private fun signUpFirstNameChanged(value: String) {
        _signUpstate.update { state ->
            state.copy(
                firstName = value
            )
        }
    }

    private fun signUpLastNameChanged(value: String) {
        _signUpstate.update { state ->
            state.copy(
                lastName = value
            )
        }
    }

    private fun signUpUsernameChanged(value: String) {
        _signUpstate.update { state ->
            state.copy(
                username = value
            )
        }
    }

    private fun signUpEmailChanged(value: String) {
        _signUpstate.update { state ->
            state.copy(
                email = value
            )
        }
    }

    private fun signUpPasswordChanged(value: String) {
        _signUpstate.update { state ->
            state.copy(
                password = value
            )
        }
    }

    private fun signUpPhoneNumberChanged(value: String) {
        _signUpstate.update { state ->
            state.copy(
                phoneNumber = value
            )
        }
    }

    private fun signUpBirthDateChanged(value: String) {
        _signUpstate.update { state ->
            state.copy(
                birthDate = value
            )
        }
    }

    private fun signUpAddress1Changed(value: String) {
        _signUpstate.update { state ->
            state.copy(
                address1 = value
            )
        }
    }

    private fun signUpAddress2Changed(value: String) {
        _signUpstate.update { state ->
            state.copy(
                address2 = value
            )
        }
    }

    private fun signUpGenderChanged(value: String) {
        _signUpstate.update { state ->
            state.copy(
                gender = value
            )
        }
    }

    private fun onSignUpSubmit() {
        if (onSignUpError()) return
        viewModelScope.launch {
            val call = authRepository.signUp(
                signUpDto = SignUpDto(
                    firstName = _state.value.signUpState.firstName,
                    lastName = _state.value.signUpState.lastName,
                    username = _state.value.signUpState.username,
                    email = _state.value.signUpState.email,
                    password = _state.value.signUpState.password,
                    phoneNumber = _state.value.signUpState.phoneNumber,
                    birthDate = _state.value.signUpState.birthDate,
                    address1 = _state.value.signUpState.address1,
                    address2 = _state.value.signUpState.address2,
                    gender = _state.value.signUpState.gender,
                )
            )

            when (call) {
                is RequestState.Error -> {
                    _state.update { state ->
                        state.copy(
                            isLoading = false,
                            errorMessage = call.error.message
                        )
                    }
                    messages.sendMessage(call.error)
                }
                is RequestState.Success -> {
                    _state.update { state ->
                        state.copy(
                            isLoading = false,
                            errorMessage = null
                        )
                    }

                    messages.sendMessage(call.data)
                    navigator.navigate(
                        destination = Destination.HomeGraph,
                        navOptions = {
                            popUpTo(Destination.AuthGraph) {
                                inclusive = true
                            }
                            launchSingleTop = true
                        }
                    )

                    resetState()
                }
                else -> {
                    _state.update { state ->
                        state.copy(
                            isLoading = true,
                            errorMessage = null
                        )
                    }
                }
            }
        }
    }

    private fun onSignInError(): Boolean {
        val validation = AuthValidator.validateSignIn(
            signIn = SignIn(
                username = _state.value.signInState.username,
                password = _state.value.signInState.password,
            )
        )
        val errors = listOfNotNull(
            validation.usernameError,
            validation.passwordError
        )
        _signInstate.update { state ->
            state.copy(
                usernameError = validation.usernameError,
                passwordError = validation.passwordError
            )
        }
        return errors.isNotEmpty()
    }

    private fun onSignUpError(): Boolean {
        val validation = AuthValidator.validateSignUp(
            signUp = SignUp(
                firstName = _state.value.signUpState.firstName,
                lastName = _state.value.signUpState.lastName,
                username = _state.value.signUpState.username,
                email = _state.value.signUpState.email,
                password = _state.value.signUpState.password,
                phoneNumber = _state.value.signUpState.phoneNumber,
                birthDate = _state.value.signUpState.birthDate,
                address1 = _state.value.signUpState.address1,
                address2 = _state.value.signUpState.address2,
                gender = _state.value.signUpState.gender,
            )
        )
        val errors = listOfNotNull(
            validation.firstNameError,
            validation.lastNameError,
            validation.usernameError,
            validation.emailError,
            validation.passwordError,
            validation.phoneNumberError,
            validation.birthDateError,
            validation.address1Error,
            validation.address2Error,
            validation.genderError
        )
        _signUpstate.update { state ->
            state.copy(
                firstNameError = validation.firstNameError,
                lastNameError = validation.lastNameError,
                usernameError = validation.usernameError,
                emailError = validation.emailError,
                passwordError = validation.passwordError,
                phoneNumberError = validation.phoneNumberError,
                birthDateError = validation.birthDateError,
                address1Error = validation.address1Error,
                address2Error = validation.address2Error,
                genderError = validation.genderError
            )
        }
        return errors.isNotEmpty()
    }

    private fun resetState() {
        _state.update { state ->
            state.copy(
                isSignIn = true,
                isLoading = false,
                errorMessage = null
            )
        }
        _signUpstate.update { state ->
            state.copy(
                firstName = "",
                lastName = "",
                username = "",
                email = "",
                password = "",
                phoneNumber = "",
                birthDate = "",
                address1 = "",
                address2 = "",
                gender = "",
                firstNameError = null,
                lastNameError = null,
                usernameError = null,
                emailError = null,
                passwordError = null,
                phoneNumberError = null,
                birthDateError = null,
                address1Error = null,
                address2Error = null,
                genderError = null,
            )
        }
        _signInstate.update { state ->
            state.copy(
                username = "",
                password = "",
                usernameError = null,
                passwordError = null,
            )
        }
    }
}
