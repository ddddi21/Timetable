package com.technokratos.auth.presentation.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.coroutineScope
import com.example.feature_auth.databinding.FragmentLoginBinding
import com.technokratos.auth.di.AuthFeatureKey
import com.technokratos.auth.di.AuthFeatureComponent
import com.technokratos.common.base.BaseFragment
import com.technokratos.common.di.FeatureUtils
import com.technokratos.common.utils.changeVisibility
import com.technokratos.common.utils.textChanges
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn

class AuthFragment : BaseFragment<AuthViewModel>() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun inject() {
        FeatureUtils.getFeature<AuthFeatureComponent>(this, AuthFeatureKey::class.java)
            .authComponentFactory()
            .create(this)
            .inject(this)
    }

    @ExperimentalCoroutinesApi
    override fun initViews() {
        initChangeTextListener()
        with(binding) {
            registrationTextView.setOnClickListener {
                viewModel.onRegistrationClicked()
            }
            loginButton.setOnClickListener {
                val email = emailInputEditText.text.toString()
                val password = passwordInputEditText.text.toString()
                viewModel.onLoginButtonClicked(email, password)
            }
        }
    }

    override fun subscribe(viewModel: AuthViewModel) {
        viewModel.authViewState.observe(viewLifecycleOwner, ::render)
    }

    private fun render(state: AuthViewState) = with(binding) {
        loginButton.isEnabled = state.isLoginButtonEnabled
        loginErrorMessageTextView.text = state.loginErrorMessage
        loginErrorMessageTextView.changeVisibility(state.isLoginErrorMessageVisible)
    }

    @ExperimentalCoroutinesApi
    private fun initChangeTextListener() = with(binding) {
        combine(
            emailInputEditText.textChanges(),
            passwordInputEditText.textChanges()
        ) { email, password ->
            viewModel.onTextChanged(email.toString(), password.toString())
        }.launchIn(viewLifecycleOwner.lifecycle.coroutineScope)
    }
}