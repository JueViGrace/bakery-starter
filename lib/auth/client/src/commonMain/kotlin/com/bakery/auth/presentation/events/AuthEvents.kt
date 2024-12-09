package com.bakery.auth.presentation.events

sealed interface AuthEvents {
    data class OnSignInUsernameChanged(val value: String) : AuthEvents
    data class OnSignInPasswordChanged(val value: String) : AuthEvents
    data object TogglePasswordVisibility : AuthEvents
    data object OnSignInSubmit : AuthEvents
    data class OnSignUpFirstNameChanged(val value: String) : AuthEvents
    data class OnSignUpLastNameChanged(val value: String) : AuthEvents
    data class OnSignUpUsernameChanged(val value: String) : AuthEvents
    data class OnSignUpEmailChanged(val value: String) : AuthEvents
    data class OnSignUpPasswordChanged(val value: String) : AuthEvents
    data class OnSignUpPhoneNumberChanged(val value: String) : AuthEvents
    data class OnSignUpBirthDateChanged(val value: String) : AuthEvents
    data class OnSignUpAddress1Changed(val value: String) : AuthEvents
    data class OnSignUpAddress2Changed(val value: String) : AuthEvents
    data class OnSignUpGenderChanged(val value: String) : AuthEvents
    data object OnSignUpSubmit : AuthEvents
}
